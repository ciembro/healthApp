package com.ciembro.healthApp.service;


import com.ciembro.healthApp.domain.drug.Drug;
import com.ciembro.healthApp.domain.user.User;
import com.ciembro.healthApp.domain.sideeffect.SideEffect;
import com.ciembro.healthApp.exception.UserNotFoundException;
import com.ciembro.healthApp.repository.DrugRepository;
import com.ciembro.healthApp.repository.SideEffectRepository;
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

    @Autowired
    private SideEffectRepository sideEffectRepository;

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
    void testFindByCommonNameFrag(){
        //given
        Drug drug = createDrug();

        drug = drugRepository.save(drug);
        Long id = drug.getId();
        //when
        List<Drug> foundDrugsAllName = drugService.findByCommonNameFrag("testCommonName");
        List<Drug> foundDrugsFragName = drugService.findByCommonNameFrag("Common");
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

    @Test
    void shouldAddDrugToUserList() throws UserNotFoundException {
        //given
        Drug drug = new Drug(
                "test",
                "test",
                "test",
                "test",
                "test",
                "http://test.com");
        drug = drugRepository.save(drug);
        User user = createUser();
        user = userRepository.save(user);

        //when
        SideEffect sideEffect = drugService.addDrugToUserList(user.getUsername(), drug);

        //then
        assertEquals(sideEffect.getUser().getId(), user.getId());
        assertEquals(sideEffect.getDrug().getId(), drug.getId());

        //cleanup
        sideEffectRepository.deleteById(sideEffect.getId());
        userRepository.deleteById(user.getId());
    }

    @Test
    void shouldRemoveDrugFromUserList() throws UserNotFoundException {
        Drug drug = new Drug(
                "test",
                "test",
                "test",
                "test",
                "test",
                "http://test.com");
        drug = drugRepository.save(drug);
        User user = createUser();
        user = userRepository.save(user);
        SideEffect sideEffect = new SideEffect();
        sideEffect.setUser(user);
        sideEffect.setDrug(drug);
        sideEffectRepository.save(sideEffect);

        //when
        drugService.removeDrugFromUserList(user.getUsername(), drug);

        //then
        assertEquals(0, user.getSideEffects().size());
        assertEquals(0, drug.getSideEffects().size());

        //cleanup
       userRepository.deleteById(user.getId());
       drugRepository.deleteById(drug.getId());

    }


    private Drug createDrug(){
       return new Drug(
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
