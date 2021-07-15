package com.ciembro.healthApp.service;

import com.ciembro.healthApp.client.DrugApiClient;
import com.ciembro.healthApp.domain.drug.Drug;
import com.ciembro.healthApp.mapper.DrugMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        List<Drug> apiDrugs = mapper.mapToDrugList(client.loadDrugListFromApi());
        if (!apiDrugs.isEmpty()){

            for (Drug apiDrug : apiDrugs){
                Drug drugFromDb = service.findByUniqueDrugId(apiDrug.getUniqueDrugId());
                if (drugFromDb != null){
                    if (!checkIfEquals(apiDrug, drugFromDb)){
                        apiDrug.setId(drugFromDb.getId());
                        apiDrug.setLastSynchAt(LocalDate.now());
                        service.save(apiDrug);
                    }
                } else {
                    apiDrug.setLastSynchAt(LocalDate.now());
                    service.save(apiDrug);
                }
            }
        }
    }

    private boolean checkIfEquals(Drug apiDrug, Drug drugFromDb){
        return apiDrug.getLeafletUrl().equals(drugFromDb.getLeafletUrl())
                && apiDrug.getBrand().equals(drugFromDb.getBrand())
                && apiDrug.getTradeName().equals(drugFromDb.getTradeName())
                && apiDrug.getInternationalName().equals(drugFromDb.getInternationalName())
                && apiDrug.getActiveSubstance().equals(drugFromDb.getActiveSubstance())
                && apiDrug.getDosage().equals(drugFromDb.getDosage());
    }

}
