package com.ciembro.healthApp.service;

import com.ciembro.healthApp.domain.Drug;
import com.ciembro.healthApp.repository.DrugRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
@RequiredArgsConstructor
public class DrugService {
    private final DrugRepository drugRepository;

    public void saveAll(List<Drug> drugs){
        for (Drug drug : drugs){
            drugRepository.save(drug);
        }
    }

    public Drug save(Drug drug){
        return drugRepository.save(drug);
    }

    public void deleteById(Long id){
        drugRepository.deleteById(id);
    }

    public List<Drug> findByActiveSubstance(String substance){

        return new ArrayList<>();
    }



}
