package com.ilelli.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDto {
    @ValidEmail
    private String mail;
    private String password;
    private String name;
    private byte[] profilePic;
}
