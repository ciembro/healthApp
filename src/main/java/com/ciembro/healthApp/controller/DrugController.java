package com.ciembro.healthApp.controller;

import com.ciembro.healthApp.domain.drug.Drug;
import com.ciembro.healthApp.domain.drug.DrugDto;
import com.ciembro.healthApp.exception.DrugNotFoundException;
import com.ciembro.healthApp.exception.UserNotFoundException;
import com.ciembro.healthApp.mapper.DrugMapper;
import com.ciembro.healthApp.service.DrugService;
import com.ciembro.healthApp.service.SideEffectService;
import com.ciembro.healthApp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class DrugController {

    private final UserService userService;
    private final DrugMapper drugMapper;
    private final DrugService drugService;
    private final SideEffectService sideEffectService;


    @GetMapping("/drugs/{textToMatch}")
    public List<DrugDto> getByName(@PathVariable String textToMatch){
        return drugMapper.mapFromDbToDrugDtoList
                (drugService.findAllMatching(textToMatch));
    }

    @GetMapping(value = "/drugs/leaflet")
    public String getDrugLeaflet(@RequestBody DrugDto drugDto) throws DrugNotFoundException {
        Drug drug = drugMapper.mapToDrug(drugDto);
        return drugService.getLeafletUrl(drug);
    }

    @PostMapping(value = "/drugs", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addDrugToUserList(Authentication authentication, @RequestBody DrugDto drugDto)
            throws DrugNotFoundException, UserNotFoundException {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Drug drug = drugMapper.mapToDrug(drugDto);
        drugService.addDrugToUserList(userDetails.getUsername(), drug);
    }

    @DeleteMapping(value = "/drugs", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteDrugFromUserList(Authentication authentication, @RequestBody DrugDto drugDto)
            throws DrugNotFoundException, UserNotFoundException {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        Drug drug = drugMapper.mapToDrug(drugDto);
        drugService.removeDrugFromUserList(userDetails.getUsername(), drug);
    }

    @GetMapping(value = "/drugs/all")
    public List<DrugDto> getUserDrugs(Authentication authentication) throws UserNotFoundException {

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        List<Drug> drugs = sideEffectService.getUserDrugs(userDetails.getUsername());
        return drugMapper.mapFromDbToDrugDtoList(drugs);

    }

}
