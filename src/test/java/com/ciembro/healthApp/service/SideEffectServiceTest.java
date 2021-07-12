package com.ciembro.healthApp.service;

import com.ciembro.healthApp.domain.drug.Drug;
import com.ciembro.healthApp.domain.user.User;
import com.ciembro.healthApp.domain.sideeffect.SideEffect;
import com.ciembro.healthApp.repository.DrugRepository;
import com.ciembro.healthApp.repository.SideEffectRepository;
import com.ciembro.healthApp.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest()

class SideEffectServiceTest {

    @Autowired
    private SideEffectService sideEffectService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DrugRepository drugRepository;

    @Autowired
    private SideEffectRepository sideEffectRepository;

    @Test
    void shouldSaveSideEffect(){
        //given
        User user = new User("user", "user@mail.com", "password");
        user = userRepository.save(user);

        Drug drug = new Drug(
                "testTradeName",
                "testCommonName",
                "testDose",
                "testBrand",
                "testSubstance",
                "http://test.com");
        drug = drugRepository.save(drug);
        SideEffect sideEffect = new SideEffect();
        sideEffect.setDrug(drug);
        sideEffect.setUser(user);
        sideEffect.setDetails("Details");

        //when
        sideEffect = sideEffectService.save(sideEffect);

        //then
        assertNotEquals(0, sideEffect.getId());
        assertEquals(user.getId(), sideEffect.getUser().getId());
        assertEquals(drug.getId(), sideEffect.getDrug().getId());

        //cleanup
        sideEffectRepository.deleteById(sideEffect.getId());
    }
}