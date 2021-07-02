package com.ciembro.healthApp.mapper;

import com.ciembro.healthApp.domain.drug.Drug;
import com.ciembro.healthApp.domain.drug.DrugDbResultDto;
import com.ciembro.healthApp.domain.drug.DrugDto;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
public class DrugMapper {

    public Drug mapToDrug(DrugDto drugDto){
        return new Drug( drugDto.getId(),
                drugDto.getDrugAttributes().getTradeName().getValue(),
                drugDto.getDrugAttributes().getCommonName().getValue(),
                drugDto.getDrugAttributes().getDose().getValue(),
                drugDto.getDrugAttributes().getBrand().getValue(),
                drugDto.getDrugAttributes().getActiveSubstance().getValue(),
                drugDto.getDrugAttributes().getLeafletUrl().getValue());
    }

    public List<Drug> mapToDrugList(List<DrugDto> drugDtoList){
        return  drugDtoList.stream()
                .map(this::mapToDrug)
                .collect(Collectors.toList());
    }

    public DrugDbResultDto mapFromDbToDrugDto(Drug drug){
        return new DrugDbResultDto(drug.getId(),
                drug.getActiveSubstance(),
                drug.getCommonName(),
                drug.getTradeName(),
                drug.getBrand(),
                drug.getLeafletUrl());
    }

    public List<DrugDbResultDto> mapFromDbToDrugDtoList(List<Drug> drugs){
        return drugs.stream()
                .map(this::mapFromDbToDrugDto)
                .collect(Collectors.toList());
    }
}
