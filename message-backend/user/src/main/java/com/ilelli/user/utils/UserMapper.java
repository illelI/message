package com.ilelli.user.utils;

import com.ilelli.user.User;
import com.ilelli.user.UserDto;

public class UserMapper {

    private UserMapper() {}
    public static User toUser(UserDto userDto) {
        User user = new User();
        user.setMail(userDto.getMail());
        user.setName(userDto.getName());
        user.setProfilePic(user.getProfilePic());
        user.setPassword(userDto.getPassword());
        return user;
    }
}
