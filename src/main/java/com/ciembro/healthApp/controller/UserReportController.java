package com.ciembro.healthApp.controller;

import com.ciembro.healthApp.domain.UserReportRowDto;
import com.ciembro.healthApp.domain.drug.DrugDto;
import com.ciembro.healthApp.exception.DrugNotFoundException;
import com.ciembro.healthApp.exception.UserNotFoundException;
import com.ciembro.healthApp.facade.UserReportFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/report")
@RequiredArgsConstructor
public class UserReportController {

    private final UserReportFacade facade;

    @GetMapping
    public List<UserReportRowDto> generateReport(Authentication authentication)
            throws UserNotFoundException {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return facade.generateReport(userDetails.getUsername());
    }

    @GetMapping(value = "/drug/{drugId}" )
    public List<UserReportRowDto> filterByDrug(Authentication authentication, @PathVariable String drugId)
            throws UserNotFoundException, DrugNotFoundException {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return facade.filterByDrug(drugId, userDetails.getUsername());
    }

}
