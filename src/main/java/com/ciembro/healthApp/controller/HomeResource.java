package com.ciembro.healthApp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeResource {

    @GetMapping("/")
    public String home(){
        return "Welcome";
    }

    @GetMapping("/user")
    public String user(){
        return "Welcome, user";
    }

    @GetMapping("/admin")
    public String admin(){
        return "Welcome admin";
    }
}
