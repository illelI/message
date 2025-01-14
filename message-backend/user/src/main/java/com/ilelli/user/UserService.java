package com.ilelli.user;

import com.ilelli.user.exceptions.UserAlreadyExistException;
import com.ilelli.user.exceptions.UserNotFoundException;
import com.ilelli.user.utils.UserMapper;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private User loadUser(UUID id) {
        return userRepository.findUserById(id).orElseThrow(UserNotFoundException::new);
    }

    public User createUser(UserDto userDto) {
        User user = UserMapper.toUser(userDto);
        if(userRepository.findUserByMail(user.getMail()).isPresent()) {
            throw new UserAlreadyExistException();
        }
        return userRepository.save(user);
    }

    public boolean sendFriendRequest(UUID senderId, UUID receiverId) {
        User receiver = loadUser(receiverId);

        if(receiver.getFriends().contains(senderId)) {
            return false;
        }

        if (receiver.getBlocked().contains(senderId)) {
            return false;
        }

        if (receiver.getInvitations().contains(senderId)) {
            return false;
        }

        receiver.addInvitation(senderId);
        userRepository.save(receiver);
        return true;
    }

    public void acceptInvitation(UUID receiverId, UUID senderId) {
        User user = loadUser(receiverId);
        user.acceptInvitation(senderId);
        userRepository.save(user);
    }

    public void rejectInvitation(UUID receiverId, UUID senderId) {
        User user = loadUser(receiverId);
        user.rejectInvitation(senderId);
        userRepository.save(user);
    }

    public void removeFromFriends(UUID userId, UUID friendId) {
        User user = loadUser(userId);
        user.removeFromFriends(friendId);
        userRepository.save(user);
    }

    public void addToBlocked(UUID userId, UUID toBlockId) {
        User user = loadUser(userId);
        user.addToBlocked(toBlockId);
        userRepository.save(user);
    }

    public void removeFromBlocked(UUID userId, UUID unblockedId) {
        User user = loadUser(userId);
        user.removeFromBlocked(unblockedId);
        userRepository.save(user);
    }

}
