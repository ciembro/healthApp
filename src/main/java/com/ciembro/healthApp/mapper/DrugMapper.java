package com.ciembro.healthApp.mapper;

import com.ciembro.healthApp.domain.drug.Drug;
import com.ciembro.healthApp.domain.drug.DrugDto;
import com.ciembro.healthApp.domain.drug.api.DrugJsonDto;
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
        return Drug.builder()
                .uniqueDrugId(uniqueDrugId)
                .internationalName(internationalName == null ? "-" : internationalName)
                .tradeName(tradeName == null ? "-" : tradeName)
                .dosage(dosage == null ? "-" : dosage)
                .brand(brand == null ? "-" : brand)
                .activeSubstance(activeSubstance == null ? "-" : activeSubstance)
                .leafletUrl(leafletUrl == null ? "-" : leafletUrl)
                .build();
    }

    public List<Drug> mapToDrugList(List<DrugJsonDto> drugJsonDtoList){
        return  drugJsonDtoList.stream()
                .map(this::mapToDrug)
                .collect(Collectors.toList());
    }

    public DrugDto mapToDrugDto(Drug drug){
        return DrugDto.builder()
                .id(drug.getId())
                .uniqueDrugId(drug.getUniqueDrugId())
                .tradeName(drug.getTradeName())
                .internationalName(drug.getInternationalName())
                .activeSubstance(drug.getActiveSubstance())
                .brand(drug.getBrand())
                .dosage(drug.getDosage())
                .leafletUrl(drug.getLeafletUrl())
                .build();
    }

    public Drug mapToDrug(DrugDto drugDto)  {
        return Drug.builder()
                .id(drugDto.getId())
                .uniqueDrugId(drugDto.getUniqueDrugId())
                .tradeName(drugDto.getTradeName())
                .internationalName(drugDto.getInternationalName())
                .activeSubstance(drugDto.getActiveSubstance())
                .brand(drugDto.getBrand())
                .dosage(drugDto.getDosage())
                .leafletUrl(drugDto.getLeafletUrl())
                .build();
    }

    public List<DrugDto> mapToDrugDtoList(List<Drug> drugs){
        return drugs.stream()
                .map(this::mapToDrugDto)
                .collect(Collectors.toList());
    }
}
