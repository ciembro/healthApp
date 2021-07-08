package com.ciembro.healthApp.controller;

import com.ciembro.healthApp.domain.drug.Drug;
import com.ciembro.healthApp.domain.drug.DrugDto;
import com.ciembro.healthApp.exception.DrugNotFoundException;
import com.ciembro.healthApp.exception.UserNotFoundException;
import com.ciembro.healthApp.mapper.DrugMapper;
import com.ciembro.healthApp.security.domain.JwtUtil;
import com.ciembro.healthApp.service.SideEffectService;
import com.ciembro.healthApp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final DrugMapper drugMapper;
    private final SideEffectService sideEffectService;

    @PostMapping(value = "/drugs", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addDrugToUserList(Authentication authentication,
                              @RequestHeader (name="Authorization") String token,
                              @RequestBody DrugDto drugDto) throws DrugNotFoundException, UserNotFoundException {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        if (jwtUtil.validateToken(token, userDetails)){
            Drug drug = drugMapper.mapToDrug(drugDto);
            userService.addDrugToUserList(userDetails.getUsername(), drug);
        }
    }

    @DeleteMapping(value = "/drugs", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteDrugFromUserList(Authentication authentication,
                              @RequestHeader (name="Authorization") String token,
                              @RequestBody DrugDto drugDto) throws DrugNotFoundException, UserNotFoundException {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        if (jwtUtil.validateToken(token, userDetails)) {
            Drug drug = drugMapper.mapToDrug(drugDto);
            userService.removeDrugFromUserList(userDetails.getUsername(), drug);
        }
    }

    @GetMapping(value = "/drugs/all")
    public List<DrugDto> getUserDrugs(Authentication authentication,
                                      @RequestHeader (name="Authorization") String token) throws UserNotFoundException {

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        if (jwtUtil.validateToken(token, userDetails)) {
            List<Drug> drugs = sideEffectService.getUserDrugs(userDetails.getUsername());
            return drugMapper.mapFromDbToDrugDtoList(drugs);
        }
        return new ArrayList<>();
    }

}
