package com.ciembro.healthApp.facade;

import com.ciembro.healthApp.domain.user.User;
import com.ciembro.healthApp.domain.user.UserToRegisterDto;
import com.ciembro.healthApp.mapper.UserMapper;
import com.ciembro.healthApp.service.UserService;
import com.ciembro.healthApp.service.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

@Component
public class RegistrationFacade {

    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper mapper;

    @Autowired
    private UserValidator userValidator;

    public boolean registerUser(@RequestBody UserToRegisterDto userDto) {
        if (userValidator.validateUserDetails(userDto)) {
            User user = mapper.mapToUser(userDto);
            userService.save(user);
            return true;
        }
        return false;
    }


}
