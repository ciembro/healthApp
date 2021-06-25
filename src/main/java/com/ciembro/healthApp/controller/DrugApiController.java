package com.ciembro.healthApp.controller;

import com.ciembro.healthApp.client.DrugApiClient;
import com.ciembro.healthApp.domain.Drug;
import com.ciembro.healthApp.domain.DrugDto;
import com.ciembro.healthApp.domain.Links;
import com.ciembro.healthApp.mapper.DrugMapper;
import com.ciembro.healthApp.service.DrugService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
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
}
