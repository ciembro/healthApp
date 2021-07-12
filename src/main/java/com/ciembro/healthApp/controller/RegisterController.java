package com.ciembro.healthApp.controller;

import com.ciembro.healthApp.domain.user.User;
import com.ciembro.healthApp.domain.user.UserToRegisterDto;
import com.ciembro.healthApp.exception.IncorrectUserDetailsException;
import com.ciembro.healthApp.mapper.UserMapper;
import com.ciembro.healthApp.service.UserService;
import com.ciembro.healthApp.service.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class RegisterController {

    private final UserService userService;
    private final UserMapper mapper;
    private final UserValidator userValidator;

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public boolean registerUser(@RequestBody UserToRegisterDto userDto){
        if (userValidator.validateUserDetails(userDto)){
            User user = mapper.mapToUser(userDto);
            userService.save(user);
            return true;
        }
        return false;
    }
}
