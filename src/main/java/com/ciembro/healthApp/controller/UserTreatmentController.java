package com.ciembro.healthApp.controller;

import com.ciembro.healthApp.domain.CreatedUserTreatmentDto;
import com.ciembro.healthApp.domain.UserTreatmentDto;
import com.ciembro.healthApp.exception.DrugNotFoundException;
import com.ciembro.healthApp.exception.UserNotFoundException;
import com.ciembro.healthApp.facade.UserTreatmentFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/treatment")
@RequiredArgsConstructor
public class UserTreatmentController {

    private final UserTreatmentFacade facade;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public CreatedUserTreatmentDto createUserTreatment(@RequestBody UserTreatmentDto treatmentDto)
                        throws UserNotFoundException, DrugNotFoundException {

        return facade.createUserTreatment(treatmentDto);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public CreatedUserTreatmentDto updateUserTreatment(@RequestBody CreatedUserTreatmentDto treatmentDto)
                        throws UserNotFoundException, DrugNotFoundException {

        return facade.updateUserTreatment(treatmentDto);
    }

    @DeleteMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteUserTreatment(@RequestBody CreatedUserTreatmentDto treatmentDto)
                        throws UserNotFoundException, DrugNotFoundException {

        facade.deleteUserTreatment(treatmentDto);
    }

    @GetMapping
    public List<CreatedUserTreatmentDto> getAllUserTreatments(Authentication authentication)
                        throws UserNotFoundException {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return facade.getAllUserTreatments(userDetails.getUsername());
    }
}
