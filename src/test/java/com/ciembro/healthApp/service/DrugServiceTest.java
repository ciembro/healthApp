package com.ciembro.healthApp.service;


import com.ciembro.healthApp.domain.drug.Drug;
import com.ciembro.healthApp.domain.user.User;
import com.ciembro.healthApp.repository.DrugRepository;
import com.ciembro.healthApp.repository.UserRepository;
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

    @Autowired
    private DrugRepository drugRepository;

    @Autowired
    private UserRepository userRepository;


    @Test
    void testSave(){
        //given
        Drug drug = createDrug();
        //when
        drug = drugService.save(drug);
        Long id = drug.getId();
        //then
        assertNotEquals(0L, id);
        //cleanup
        drugRepository.deleteById(id);
    }

    @Test
    void testFindByActiveSubstanceFrag(){
        //given
        Drug drug = createDrug();

        drug = drugRepository.save(drug);
        Long id = drug.getId();
        //when
        List<Drug> foundDrugsAllName = drugService.findByActiveSubstanceFrag("testSubstance");
        List<Drug> foundDrugsFragName = drugService.findByActiveSubstanceFrag("ance");
        //then
        assertEquals(1, foundDrugsAllName.size());
        assertEquals(1, foundDrugsFragName.size());
        //cleanup
        drugRepository.deleteById(id);
    }

    @Test
    void testFindInternationalNameFrag(){
        //given
        Drug drug = createDrug();

        drug = drugRepository.save(drug);
        Long id = drug.getId();
        //when
        List<Drug> foundDrugsAllName = drugService.findByInternationalNameFrag("testCommonName");
        List<Drug> foundDrugsFragName = drugService.findByInternationalNameFrag("Common");
        //then
        assertEquals(1, foundDrugsAllName.size());
        assertEquals(1, foundDrugsFragName.size());
        //cleanup
        drugRepository.deleteById(id);
    }

    @Test
    void testFindByTradeNameFrag(){
        //given
        Drug drug = createDrug();

        drug = drugService.save(drug);
        Long id = drug.getId();
        //when
        List<Drug> foundDrugsAllName = drugService.findByTradeNameFrag("testTradeName");
        List<Drug> foundDrugsFragName = drugService.findByTradeNameFrag("Trade");
        //then
        assertEquals(1, foundDrugsAllName.size());
        assertEquals(1, foundDrugsFragName.size());
        //cleanup
        drugRepository.deleteById(id);
    }

    @Test
    void testGetLeaflet(){
        //given
        Drug drug = createDrug();
        drug = drugService.save(drug);
        Long id = drug.getId();

        //when
        String leafletUrl = drugService.getLeafletUrl(drug);
        //then
        assertEquals("http://test.com", leafletUrl);
        //cleanup
        drugRepository.deleteById(id);
    }


    private Drug createDrug(){
       return new Drug(1,
                "testTradeName",
                "testCommonName",
                "testDose",
                "testBrand",
                "testSubstance",
                "http://test.com");
    }

    private User createUser(){
        return new User("user", "user@email.com", "pass");
    }

}
