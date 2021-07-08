package com.ciembro.healthApp.domain.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginCredentials {

    private String username;
    private String email;

}
