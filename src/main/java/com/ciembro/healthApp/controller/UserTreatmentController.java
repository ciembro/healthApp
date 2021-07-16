package com.ciembro.healthApp.controller;

import com.ciembro.healthApp.domain.CreatedUserTreatmentDto;
import com.ciembro.healthApp.domain.UserTreatmentDto;
import com.ciembro.healthApp.exception.DrugNotFoundException;
import com.ciembro.healthApp.exception.UserNotFoundException;
import com.ciembro.healthApp.facade.UserTreatmentFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/treatment")
@RequiredArgsConstructor
public class UserTreatmentController {

    private final UserTreatmentFacade facade;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public CreatedUserTreatmentDto createUserTreatment(UserTreatmentDto treatmentDto)
                        throws UserNotFoundException, DrugNotFoundException {

        return facade.createUserTreatment(treatmentDto);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public CreatedUserTreatmentDto updateUserTreatment(CreatedUserTreatmentDto treatmentDto)
                        throws UserNotFoundException, DrugNotFoundException {

        return facade.updateUserTreatment(treatmentDto);
    }

    @DeleteMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteUserTreatment(CreatedUserTreatmentDto treatmentDto)
                        throws UserNotFoundException, DrugNotFoundException {

        facade.deleteUserTreatment(treatmentDto);
    }

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<CreatedUserTreatmentDto> getAllUserTreatments(Authentication authentication)
                        throws UserNotFoundException {

        return facade.getAllUserTreatments(authentication);
    }
}
