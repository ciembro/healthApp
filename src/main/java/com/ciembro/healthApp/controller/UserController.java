package com.ciembro.healthApp.controller;

import com.ciembro.healthApp.domain.drug.Drug;
import com.ciembro.healthApp.domain.drug.DrugDbResultDto;
import com.ciembro.healthApp.exception.DrugNotFoundException;
import com.ciembro.healthApp.exception.UserNotFoundException;
import com.ciembro.healthApp.mapper.DrugMapper;
import com.ciembro.healthApp.mapper.UserMapper;
import com.ciembro.healthApp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final DrugMapper drugMapper;

    @PostMapping(value = "/drugs", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addDrugToUserList(Authentication authentication,
                              @RequestHeader (name="Authorization") String token,
                              @RequestBody DrugDbResultDto drugDto) throws DrugNotFoundException, UserNotFoundException {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        Drug drug = drugMapper.mapToDrug(drugDto);
        userService.addDrugToUserList(username, drug);
    }

    @DeleteMapping(value = "/drugs", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteDrugFromUserList(Authentication authentication,
                              @RequestHeader (name="Authorization") String token,
                              @RequestBody DrugDbResultDto drugDto) throws DrugNotFoundException, UserNotFoundException {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        Drug drug = drugMapper.mapToDrug(drugDto);
        userService.removeDrugFromUserList(username, drug);
    }

    @GetMapping(value = "/drugs/all")
    public List<DrugDbResultDto> getUserDrugs(Authentication authentication,
                                              @RequestHeader (name="Authorization") String token) throws UserNotFoundException {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        String username = userDetails.getUsername();
        List<Drug> drugs = userService.getUserDrugs(username);
        return drugMapper.mapFromDbToDrugDtoList(drugs);
    }


}
