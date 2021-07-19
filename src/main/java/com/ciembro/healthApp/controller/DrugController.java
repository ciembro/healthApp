package com.ciembro.healthApp.controller;

import com.ciembro.healthApp.domain.drug.DrugDto;
import com.ciembro.healthApp.exception.UserNotFoundException;
import com.ciembro.healthApp.facade.DrugFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/drugs")
@RequiredArgsConstructor
public class DrugController {

    private final DrugFacade facade;

    @GetMapping("/search/{text}")
    public List<DrugDto> searchMatchingDrugs(@PathVariable String text){
        return facade.searchMatchingDrugs(text);
    }

    @GetMapping("/{date}")
    public List<DrugDto> getDrugByDate(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
                               Authentication authentication) throws UserNotFoundException {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return facade.getDrugByDate(date, userDetails.getUsername());
    }

    @GetMapping("/user")
    public List<DrugDto> getAllByUser(Authentication authentication) throws UserNotFoundException {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return facade.getAllByUser(userDetails.getUsername());
    }
}
