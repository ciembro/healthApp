package com.ciembro.healthApp.service;

import com.ciembro.healthApp.client.DrugApiClient;
import com.ciembro.healthApp.domain.drug.Drug;
import com.ciembro.healthApp.mapper.DrugMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrugApiService {

    @Autowired
    private DrugApiClient client;

    @Autowired
    private DrugService service;

    @Autowired
    private DrugMapper mapper;

    public void updateDrugList(){
        List<Drug> drugs = mapper.mapToDrugList(client.getDrugList());
        service.saveAll(drugs);
        System.out.println(drugs.size());
    }
}
