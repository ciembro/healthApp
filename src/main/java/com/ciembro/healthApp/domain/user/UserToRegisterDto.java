package com.ciembro.healthApp.domain.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserToRegisterDto {

    private String username;
    private String email;
    private String password;


}
