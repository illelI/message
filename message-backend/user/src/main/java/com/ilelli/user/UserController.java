package com.ilelli.user;

import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.Principal;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public UUID createUser(@RequestBody @Valid UserDto userDto, @RequestParam("profilePic") @Nullable MultipartFile image) {
        try {
            if (image != null) {
                userDto.setProfilePic(image.getBytes());
            }
        } catch (IOException ex) {
            //profile pic may be null
        }
        User user = userService.createUser(userDto);
        return user.getId();
    }

    @PostMapping("/invite")
    public HttpStatus sendInvitation(@RequestParam UUID receiverId, Principal principal) {
        UUID senderId = UUID.fromString(principal.getName());
        return userService.sendFriendRequest(senderId, receiverId) ? HttpStatus.OK : HttpStatus.FORBIDDEN;
    }

    @PostMapping("/acceptInvitation")
    public HttpStatus acceptInvitation(@RequestParam UUID senderId, Principal principal) {
        UUID receiverId = UUID.fromString(principal.getName());
        userService.acceptInvitation(receiverId, senderId);
        return HttpStatus.OK;
    }

    @PostMapping("/rejectInvitation")
    public HttpStatus rejectInvitation(@RequestParam UUID senderId, Principal principal) {
        UUID receiverId = UUID.fromString(principal.getName());
        userService.rejectInvitation(receiverId, senderId);
        return HttpStatus.OK;
    }

    @PostMapping("/removeFromFriends")
    public HttpStatus removeFromFriends(@RequestParam UUID friendId, Principal principal) {
        UUID userId = UUID.fromString(principal.getName());
        userService.removeFromFriends(userId, friendId);
        return HttpStatus.OK;
    }

    @PostMapping("/block")
    public HttpStatus blockUser(@RequestParam UUID toBlockId, Principal principal) {
        UUID userId = UUID.fromString(principal.getName());
        userService.addToBlocked(userId, toBlockId);
        return HttpStatus.OK;
    }

    @PostMapping("/unblock")
    public HttpStatus unblockUser(@RequestParam UUID unblockedId, Principal principal) {
        UUID userId = UUID.fromString(principal.getName());
        userService.removeFromBlocked(userId, unblockedId);
        return HttpStatus.OK;
    }

}
