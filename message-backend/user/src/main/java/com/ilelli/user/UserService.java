package com.ilelli.user;

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

    public boolean createUser(UserDto userDto) {
        User user = UserMapper.toUser(userDto);
        if(userRepository.findUserByMail(user.getMail()).isPresent()) {
            return false;
        }
        userRepository.save(user);
        return true;
    }
}
