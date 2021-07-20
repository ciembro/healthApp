package com.ciembro.healthApp.facade;

import com.ciembro.healthApp.exception.UserNotFoundException;
import com.ciembro.healthApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserFacade {

    @Autowired
    private UserService userService;

    public void changeUserLocation(String location, String username) throws UserNotFoundException {
        userService.changeUserLocation(location, username);
    }
}
