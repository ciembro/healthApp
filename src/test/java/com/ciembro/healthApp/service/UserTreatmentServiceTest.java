package com.ciembro.healthApp.service;

import com.ciembro.healthApp.domain.UserTreatment;
import com.ciembro.healthApp.domain.drug.Drug;
import com.ciembro.healthApp.domain.user.User;
import com.ciembro.healthApp.exception.UserNotFoundException;
import com.ciembro.healthApp.repository.DrugRepository;
import com.ciembro.healthApp.repository.UserRepository;
import com.ciembro.healthApp.repository.UserTreatmentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest()
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserTreatmentServiceTest {

    @Autowired
    private UserTreatmentRepository userTreatmentRepository;

    @Autowired
    private DrugRepository drugRepository;

    @Autowired
    private UserTreatmentService userTreatmentService;

    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldGetAllUserTreatments() {
        //given
        User user = new User("user", "Krakow",
                "user@email.com", "password");
        user = userRepository.save(user);
        Drug drug = createDrug();
        drug = drugRepository.save(drug);

        UserTreatment treatment = getUserTreatment();
        treatment.setUser(user);
        treatment.setDrug(drug);
        treatment = userTreatmentRepository.save(treatment);

        //when
        List<UserTreatment> treatments = userTreatmentService.getAllUserTreatments(user.getId());
        //then
        assertEquals(1, treatments.size());

        //cleanup
        userTreatmentRepository.deleteById(treatment.getId());
        drugRepository.deleteById(drug.getId());
        userRepository.deleteById(user.getId());

    }

    @Test
    void shouldFindAllBetweenDates() throws UserNotFoundException {
        //given
        User user = new User("user", "Krakow",
                "user@email.com", "password");
        user = userRepository.save(user);
        Drug drug = createDrug();
        drug = drugRepository.save(drug);

        UserTreatment treatment = getUserTreatment();
        treatment.setUser(user);
        treatment.setDrug(drug);
        treatment = userTreatmentRepository.save(treatment);

        //when
        List<UserTreatment> treatments = userTreatmentService
                .findAllBetweenDates(LocalDate.now().plusDays(1), user.getUsername());
        //then
        assertEquals(1, treatments.size());

        //cleanup
        userTreatmentRepository.deleteById(treatment.getId());
        drugRepository.deleteById(drug.getId());
        userRepository.deleteById(user.getId());
    }

    @Test
    void shouldFindAllBetweenDatesWhenNoMatches() throws UserNotFoundException {
        //given
        User user = new User("user", "Krakow",
                "user@email.com", "password");
        user = userRepository.save(user);
        Drug drug = createDrug();
        drug = drugRepository.save(drug);

        UserTreatment treatment = getUserTreatment();
        treatment.setUser(user);
        treatment.setDrug(drug);
        treatment = userTreatmentRepository.save(treatment);

        //when
        List<UserTreatment> treatments = userTreatmentService
                .findAllBetweenDates(LocalDate.now().plusDays(10), user.getUsername());
        //then
        assertEquals(0, treatments.size());

        //cleanup
        userTreatmentRepository.deleteById(treatment.getId());
        drugRepository.deleteById(drug.getId());
        userRepository.deleteById(user.getId());
    }

    private UserTreatment getUserTreatment(){
        UserTreatment treatment = new UserTreatment();
        treatment.setId(1L);
        treatment.setStartedAt(LocalDate.now());
        treatment.setFinishedAt(LocalDate.now().plusDays(5));
        return treatment;
    }
    private Drug createDrug(){
        return Drug.builder()
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