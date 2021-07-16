package com.ciembro.healthApp.controller;

import com.ciembro.healthApp.domain.user.UserToRegisterDto;
import com.ciembro.healthApp.facade.RegistrationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationFacade facade;

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean registerUser(@RequestBody UserToRegisterDto userDto){
       return facade.registerUser(userDto);
    }
}
