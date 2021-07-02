package com.ciembro.healthApp.domain.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class UserToRegisterDto {

    private String username;
    private String email;
    private String password;


}
