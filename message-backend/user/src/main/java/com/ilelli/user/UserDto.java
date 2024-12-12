package com.ilelli.user;

import com.ilelli.user.utils.ValidEmail;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class UserDto {
    @ValidEmail
    private String mail;
    private String password;
    private String name;
    @Nullable
    @Setter
    private byte[] profilePic;
}
