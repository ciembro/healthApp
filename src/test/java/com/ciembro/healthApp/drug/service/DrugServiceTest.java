package com.ciembro.healthApp.drug.service;


import com.ciembro.healthApp.domain.Drug;
import com.ciembro.healthApp.service.DrugService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@RunWith(SpringRunner.class)
@SpringBootTest()
public class DrugServiceTest {

    @Autowired
    private DrugService drugService;

    @Test
    void testSave(){
        //given
        Drug drug = new Drug("testDrugId",
                "testTradeName",
                "testCommonName",
                "testDose",
                "testBrand",
                "testSubstance",
                "http://test.com");
        //when
        drug = drugService.save(drug);
        Long id = drug.getId();
        //then
        assertNotEquals(0L, id);
        //cleanup
        drugService.deleteById(id);
    }

    @Test
    void testFindByActiveSubstanceFrag(){
        //given
        Drug drug = new Drug("testDrugId",
                "testTradeName",
                "testCommonName",
                "testDose",
                "testBrand",
                "testSubstance",
                "http://test.com");

        drug = drugService.save(drug);
        Long id = drug.getId();
        //when
        List<Drug> foundDrugsAllName = drugService.findByActiveSubstanceFrag("testSubstance");
        List<Drug> foundDrugsFragName = drugService.findByActiveSubstanceFrag("ance");
        //then
        assertEquals(1, foundDrugsAllName.size());
        assertEquals(1, foundDrugsFragName.size());
        //cleanup
        drugService.deleteById(id);
    }

    @Test
    void testFindByCommonNameFrag(){
        //given
        Drug drug = new Drug("testDrugId",
                "testTradeName",
                "testCommonName",
                "testDose",
                "testBrand",
                "testSubstance",
                "http://test.com");

        drug = drugService.save(drug);
        Long id = drug.getId();
        //when
        List<Drug> foundDrugsAllName = drugService.findByCommonNameFrag("testCommonName");
        List<Drug> foundDrugsFragName = drugService.findByCommonNameFrag("Common");
        //then
        assertEquals(1, foundDrugsAllName.size());
        assertEquals(1, foundDrugsFragName.size());
        //cleanup
        drugService.deleteById(id);
    }

    @Test
    void testFindByTradeNameFrag(){
        //given
        Drug drug = new Drug("testDrugId",
                "testTradeName",
                "testCommonName",
                "testDose",
                "testBrand",
                "testSubstance",
                "http://test.com");

        drug = drugService.save(drug);
        Long id = drug.getId();
        //when
        List<Drug> foundDrugsAllName = drugService.findByTradeNameFrag("testTradeName");
        List<Drug> foundDrugsFragName = drugService.findByTradeNameFrag("Trade");
        //then
        assertEquals(1, foundDrugsAllName.size());
        assertEquals(1, foundDrugsFragName.size());
        //cleanup
        drugService.deleteById(id);
    }


}
