package com.ciembro.healthApp.mapper;

import com.ciembro.healthApp.domain.drug.Drug;
import com.ciembro.healthApp.domain.drug.DrugDto;
import com.ciembro.healthApp.domain.drug.api.DrugJsonDto;
import com.ciembro.healthApp.exception.DrugNotFoundException;
import com.ciembro.healthApp.repository.DrugRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DrugMapper {

    private final DrugRepository drugRepository;


    public Drug mapToDrug(DrugJsonDto drugJsonDto){
        int uniqueDrugId = drugJsonDto.getDrugJsonAttributes().getUniqueDrugId().getValue();
        String tradeName = drugJsonDto.getDrugJsonAttributes().getTradeName().getValue();
        String internationalName = drugJsonDto.getDrugJsonAttributes().getInternationalName().getValue();
        String dosage = drugJsonDto.getDrugJsonAttributes().getDosage().getValue();
        String brand = drugJsonDto.getDrugJsonAttributes().getBrand().getValue();
        String activeSubstance = drugJsonDto.getDrugJsonAttributes().getActiveSubstance().getValue();
        String leafletUrl = drugJsonDto.getDrugJsonAttributes().getLeafletUrl().getValue();
        return new Drug(
                uniqueDrugId,
                tradeName == null ? "-" : tradeName,
                internationalName == null ? "-" : internationalName,
                dosage == null ? "-" : dosage,
                brand == null ? "-" : brand,
                activeSubstance == null ? "-" : activeSubstance,
                leafletUrl == null ? "-" : leafletUrl);
    }

    public List<Drug> mapToDrugList(List<DrugJsonDto> drugJsonDtoList){
        return  drugJsonDtoList.stream()
                .map(this::mapToDrug)
                .collect(Collectors.toList());
    }

    public DrugDto mapFromDbToDrugDto(Drug drug){
        return new DrugDto(drug.getId(),
                drug.getUniqueDrugId(),
                drug.getTradeName(),
                drug.getInternationalName(),
                drug.getDosage(),
                drug.getBrand(),
                drug.getActiveSubstance(),
                drug.getLeafletUrl());
    }

    public Drug mapToDrug(DrugDto drugDto) throws DrugNotFoundException {
        return  drugRepository.findById(drugDto.getId()).
                orElseThrow(DrugNotFoundException::new);
    }

    public List<DrugDto> mapFromDbToDrugDtoList(List<Drug> drugs){
        return drugs.stream()
                .map(this::mapFromDbToDrugDto)
                .collect(Collectors.toList());
    }
}
