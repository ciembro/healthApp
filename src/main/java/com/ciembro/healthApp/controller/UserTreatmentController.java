package com.ciembro.healthApp.controller;

import com.ciembro.healthApp.domain.UserTreatmentDto;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("v1/treatment")
public class UserTreatmentController {

    @PostMapping()
    public UserTreatmentDto createUserTreatment(){

        return new UserTreatmentDto();
    }

    @PutMapping()
    public UserTreatmentDto updateUserTreatment(){

        return new UserTreatmentDto();
    }

    @DeleteMapping
    public void deleteUserTreatment(){

    }

    @GetMapping
    public List<UserTreatmentDto> getAllUserTreatments(){

        return new ArrayList<>();
    }
}
