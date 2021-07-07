package com.ciembro.healthApp.controller;

import com.ciembro.healthApp.client.DrugApiClient;
import com.ciembro.healthApp.domain.drug.Drug;
import com.ciembro.healthApp.domain.drug.DrugDbResultDto;
import com.ciembro.healthApp.exception.DrugNotFoundException;
import com.ciembro.healthApp.mapper.DrugMapper;
import com.ciembro.healthApp.service.DrugService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class DrugApiController {

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
    public List<DrugDbResultDto> getByName(@PathVariable String textToMatch){
        return mapper.mapFromDbToDrugDtoList
                (service.findAllMatching(textToMatch));
    }

    @GetMapping(value = "/drugs/leaflet")
    public String getDrugLeaflet(@RequestBody DrugDbResultDto drugDto) throws DrugNotFoundException {
        Drug drug = mapper.mapToDrug(drugDto);
        return service.getLeafletUrl(drug);
    }


}
