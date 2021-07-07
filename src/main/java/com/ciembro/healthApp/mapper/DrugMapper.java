package com.ciembro.healthApp.mapper;

import com.ciembro.healthApp.domain.drug.Drug;
import com.ciembro.healthApp.domain.drug.DrugDbResultDto;
import com.ciembro.healthApp.domain.drug.DrugDto;
import com.ciembro.healthApp.exception.DrugNotFoundException;
import com.ciembro.healthApp.repository.DrugRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DrugMapper {

    private final DrugRepository drugRepository;

    public Drug mapToDrug(DrugDto drugDto){
        return new Drug(
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
                drug.getTradeName(),
                drug.getCommonName(),
                drug.getDose(),
                drug.getBrand(),
                drug.getActiveSubstance(),
                drug.getLeafletUrl());
    }

    public Drug mapToDrug(DrugDbResultDto drugDto) throws DrugNotFoundException {
        return  drugRepository.findById(drugDto.getId()).
                orElseThrow(DrugNotFoundException::new);
    }

    public List<DrugDbResultDto> mapFromDbToDrugDtoList(List<Drug> drugs){
        return drugs.stream()
                .map(this::mapFromDbToDrugDto)
                .collect(Collectors.toList());
    }
}
