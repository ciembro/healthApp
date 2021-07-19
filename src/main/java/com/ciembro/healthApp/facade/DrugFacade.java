package com.ciembro.healthApp.facade;

import com.ciembro.healthApp.domain.drug.Drug;
import com.ciembro.healthApp.domain.drug.DrugDto;
import com.ciembro.healthApp.exception.UserNotFoundException;
import com.ciembro.healthApp.mapper.DrugMapper;
import com.ciembro.healthApp.service.DrugApiService;
import com.ciembro.healthApp.service.DrugService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DrugFacade {

    private final DrugApiService drugApiService;
    private final DrugMapper drugMapper;
    private final DrugService drugService;

    public List<DrugDto> searchMatchingDrugs(String text){
        return drugMapper.mapToDrugDtoList
                (drugService.findAllMatching(text));
    }

    public List<DrugDto> getDrugByDate(LocalDate date, String username) throws UserNotFoundException {
        List<Drug> drugs = drugService.findAllByDate(date, username);
        return drugMapper.mapToDrugDtoList(drugs);
    }

    public List<DrugDto> getAllByUser(String username) throws UserNotFoundException {
        Set<Drug> drugs = drugService.getAllByUser(username);
        return drugs.stream()
                .map(drugMapper::mapToDrugDto)
                .collect(Collectors.toList());
    }

    @Scheduled(cron = "0 0 0 * * *")
    public void updateDrugDatabase(){
        drugApiService.updateDrugList();
    }
}
