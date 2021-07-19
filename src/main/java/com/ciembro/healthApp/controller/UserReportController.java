package com.ciembro.healthApp.controller;

import com.ciembro.healthApp.domain.UserReportRowDto;
import com.ciembro.healthApp.domain.drug.DrugDto;
import com.ciembro.healthApp.exception.UserNotFoundException;
import com.ciembro.healthApp.facade.UserReportFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/drug")
    public List<UserReportRowDto> filterByDrug(@RequestBody DrugDto drugDto, Authentication authentication)
            throws UserNotFoundException {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return facade.filterByDrug(drugDto, userDetails.getUsername());
    }

}
