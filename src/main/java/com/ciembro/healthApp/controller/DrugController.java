package com.ciembro.healthApp.controller;

import com.ciembro.healthApp.domain.drug.Drug;
import com.ciembro.healthApp.domain.drug.DrugDto;
import com.ciembro.healthApp.exception.DrugNotFoundException;
import com.ciembro.healthApp.mapper.DrugMapper;
import com.ciembro.healthApp.service.DrugService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/drugs")
@RequiredArgsConstructor
public class DrugController {

    private final DrugMapper drugMapper;
    private final DrugService drugService;

    @GetMapping("/search/{text}")
    public List<DrugDto> searchMatchingDrugs(@PathVariable String text){
        return drugMapper.mapFromDbToDrugDtoList
                (drugService.findAllMatching(text));
    }

    @GetMapping("/{id}")
    public DrugDto getDrugById(@PathVariable long id) throws DrugNotFoundException {
        Drug drug = drugService.findById(id);
        return drugMapper.mapFromDbToDrugDto(drug);
    }


}
