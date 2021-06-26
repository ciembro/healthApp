package com.ciembro.healthApp.drug;


import com.ciembro.healthApp.domain.Drug;
import com.ciembro.healthApp.repository.DrugRepository;
import com.ciembro.healthApp.service.DrugService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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

        drugService.deleteById(id);
    }


    @Test
    void testFindByActiveSubstance(){
        //given

    }

}
