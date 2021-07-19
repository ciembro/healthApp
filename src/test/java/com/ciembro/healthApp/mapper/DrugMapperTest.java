package com.ciembro.healthApp.mapper;

import com.ciembro.healthApp.domain.drug.Drug;
import com.ciembro.healthApp.domain.drug.DrugDto;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest()
class DrugMapperTest {

    @Autowired
    private DrugMapper drugMapper;

    @Test
    void shouldMapToDrug(){
        //given
        DrugDto drugDto = createDrugDto();

        //when
        Drug drug = drugMapper.mapToDrug(drugDto);

        //then
        assertEquals(drugDto.getId(), drug.getId());
        assertEquals(drugDto.getUniqueDrugId(), drug.getUniqueDrugId());
        assertEquals(drugDto.getInternationalName(), drug.getInternationalName());
        assertEquals(drugDto.getTradeName(), drug.getTradeName());
        assertEquals(drugDto.getActiveSubstance(), drug.getActiveSubstance());
        assertEquals(drugDto.getBrand(), drug.getBrand());
        assertEquals(drugDto.getDosage(), drug.getDosage());
        assertEquals(drugDto.getLeafletUrl(), drug.getLeafletUrl());
    }

    @Test
    void shouldMapToDrugDto(){
        //given
        Drug drug = createDrug();

        //when
        DrugDto drugDto = drugMapper.mapToDrugDto(drug);

        //then
        assertEquals(drug.getId(), drugDto.getId());
        assertEquals(drug.getUniqueDrugId(), drugDto.getUniqueDrugId());
        assertEquals(drug.getInternationalName(), drugDto.getInternationalName());
        assertEquals(drug.getTradeName(), drugDto.getTradeName());
        assertEquals(drug.getActiveSubstance(), drugDto.getActiveSubstance());
        assertEquals(drug.getBrand(), drugDto.getBrand());
        assertEquals(drug.getDosage(), drugDto.getDosage());
        assertEquals(drug.getLeafletUrl(), drugDto.getLeafletUrl());
    }

    @Test
    void shouldMapToDrugDtoList(){
        //given
        List<Drug> drugs = List.of(createDrug());

        //when
        List<DrugDto> drugDtos = drugMapper.mapToDrugDtoList(drugs);

        //then
        assertEquals(drugs.size(), drugDtos.size());
        assertEquals(drugs.get(0).getId(), drugDtos.get(0).getId());
        assertEquals(drugs.get(0).getUniqueDrugId(), drugDtos.get(0).getUniqueDrugId());
        assertEquals(drugs.get(0).getInternationalName(), drugDtos.get(0).getInternationalName());
        assertEquals(drugs.get(0).getTradeName(), drugDtos.get(0).getTradeName());
        assertEquals(drugs.get(0).getActiveSubstance(), drugDtos.get(0).getActiveSubstance());
        assertEquals(drugs.get(0).getBrand(), drugDtos.get(0).getBrand());
        assertEquals(drugs.get(0).getDosage(), drugDtos.get(0).getDosage());
        assertEquals(drugs.get(0).getLeafletUrl(), drugDtos.get(0).getLeafletUrl());
    }

    private Drug createDrug(){
        return Drug.builder()
                .id(1L)
                .uniqueDrugId(1)
                .internationalName("international name")
                .tradeName("trade name")
                .activeSubstance("active substance")
                .brand("brand")
                .dosage("dosage")
                .leafletUrl("http://test.com")
                .build();
    }

    private DrugDto createDrugDto(){
        return DrugDto.builder()
                .id(1L)
                .uniqueDrugId(1)
                .internationalName("international name")
                .tradeName("trade name")
                .activeSubstance("active substance")
                .brand("brand")
                .dosage("dosage")
                .leafletUrl("http://test.com")
                .build();
    }

}