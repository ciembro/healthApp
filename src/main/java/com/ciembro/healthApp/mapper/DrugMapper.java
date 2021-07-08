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
        return new Drug(
                drugJsonDto.getDrugJsonAttributes().getTradeName().getValue(),
                drugJsonDto.getDrugJsonAttributes().getCommonName().getValue(),
                drugJsonDto.getDrugJsonAttributes().getDose().getValue(),
                drugJsonDto.getDrugJsonAttributes().getBrand().getValue(),
                drugJsonDto.getDrugJsonAttributes().getActiveSubstance().getValue(),
                drugJsonDto.getDrugJsonAttributes().getLeafletUrl().getValue());
    }

    public List<Drug> mapToDrugList(List<DrugJsonDto> drugJsonDtoList){
        return  drugJsonDtoList.stream()
                .map(this::mapToDrug)
                .collect(Collectors.toList());
    }

    public DrugDto mapFromDbToDrugDto(Drug drug){
        return new DrugDto(drug.getId(),
                drug.getTradeName(),
                drug.getCommonName(),
                drug.getDose(),
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
