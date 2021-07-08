package com.ciembro.healthApp.controller;

import com.ciembro.healthApp.client.DrugApiClient;
import com.ciembro.healthApp.domain.drug.Drug;
import com.ciembro.healthApp.domain.drug.DrugDto;
import com.ciembro.healthApp.exception.DrugNotFoundException;
import com.ciembro.healthApp.mapper.DrugMapper;
import com.ciembro.healthApp.security.domain.JwtUtil;
import com.ciembro.healthApp.service.DrugService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class DrugApiController {

    private final JwtUtil jwtUtil;
    private final DrugApiClient client;
    private final DrugMapper mapper;
    private final DrugService service;

    @GetMapping(value = "/drugs")
    public void getDrugList(Authentication authentication){
        List<Drug> drugs = mapper.mapToDrugList(client.getDrugList());
        service.saveAll(drugs);
        System.out.println(drugs.size());
    }

    @GetMapping("/drugs/{textToMatch}")
    public List<DrugDto> getByName(Authentication authentication,
                                   @RequestHeader(name="Authorization") String token,
                                   @PathVariable String textToMatch){
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        if (jwtUtil.validateToken(token, userDetails)) {
            return mapper.mapFromDbToDrugDtoList
                    (service.findAllMatching(textToMatch));
        }
        return new ArrayList<>();
    }

    @GetMapping(value = "/drugs/leaflet")
    public String getDrugLeaflet(Authentication authentication,
                                 @RequestHeader(name="Authorization") String token,
                                 @RequestBody DrugDto drugDto) throws DrugNotFoundException {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        if (jwtUtil.validateToken(token, userDetails)) {
            Drug drug = mapper.mapToDrug(drugDto);
            return service.getLeafletUrl(drug);
        }
        return "";
    }


}
