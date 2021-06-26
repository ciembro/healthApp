package com.ciembro.healthApp.mapper;

import com.ciembro.healthApp.domain.drug.Drug;
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
}
