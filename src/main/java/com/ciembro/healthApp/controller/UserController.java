package com.ciembro.healthApp.controller;

import com.ciembro.healthApp.exception.UserNotFoundException;
import com.ciembro.healthApp.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserFacade facade;

    @PutMapping("/{username}/{location}")
    public void changeUserLocation(@PathVariable String username, @PathVariable String location) throws UserNotFoundException {
        facade.changeUserLocation(location, username);
    }
}
