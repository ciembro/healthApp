package com.ciembro.healthApp.controller;

import com.ciembro.healthApp.client.DrugApiClient;
import com.ciembro.healthApp.domain.drug.Drug;
import com.ciembro.healthApp.domain.drug.DrugDbResultDto;
import com.ciembro.healthApp.domain.drug.DrugDto;
import com.ciembro.healthApp.mapper.DrugMapper;
import com.ciembro.healthApp.service.DrugService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class DrugApiController {

    private final DrugApiClient client;
    private final DrugMapper mapper;
    private final DrugService service;

    @GetMapping("/drugs")
    public void getDrugList(){
        List<Drug> drugs = mapper.mapToDrugList(client.getDrugList());
        service.saveAll(drugs);
        System.out.println(drugs.size());
    }

    @GetMapping("/drugs/{textToMatch}")
    public List<DrugDbResultDto> getByName(@PathVariable String textToMatch){
        return mapper.mapFromDbToDrugDtoList
                (service.findAllMatching(textToMatch));
    }

    @PostMapping(value = "/drugs", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addToUserList(){

    }
}
