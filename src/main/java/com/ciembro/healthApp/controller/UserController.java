package com.ciembro.healthApp.controller;

import com.ciembro.healthApp.mapper.DrugMapper;
import com.ciembro.healthApp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final DrugMapper drugMapper;


}
