package com.ciembro.healthApp.mapper;

import com.ciembro.healthApp.domain.CreatedUserTreatmentDto;
import com.ciembro.healthApp.domain.UserTreatment;
import com.ciembro.healthApp.domain.UserTreatmentDto;
import com.ciembro.healthApp.domain.drug.Drug;
import com.ciembro.healthApp.domain.drug.DrugDto;
import com.ciembro.healthApp.domain.user.User;
import com.ciembro.healthApp.domain.user.UserToRegisterDto;
import com.ciembro.healthApp.exception.DrugNotFoundException;
import com.ciembro.healthApp.exception.UserNotFoundException;
import com.ciembro.healthApp.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest()
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserTreatmentMapperTest {

    @Autowired
    private UserTreatmentMapper mapper;

    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldMapToUserTreatment() throws UserNotFoundException, DrugNotFoundException {
        //given
        User user = new User("user", "Krakow",
                "user@email.com", "password");
        userRepository.save(user);
        UserTreatmentDto treatmentDto = getUserTreatmentDto();

        //when
        UserTreatment treatment = mapper.mapToUserTreatment(treatmentDto);

        //then
        assertEquals(treatmentDto.getUsername(), treatment.getUser().getUsername());
        assertEquals(treatmentDto.getStartedAt(), treatment.getStartedAt());
        assertEquals(treatmentDto.getFinishedAt(), treatment.getFinishedAt());
        assertEquals(treatmentDto.getDrugDto().getId(), treatment.getDrug().getId());
        assertEquals(treatmentDto.getDrugDto().getUniqueDrugId(), treatment.getDrug().getUniqueDrugId());
        assertEquals(treatmentDto.getDrugDto().getInternationalName(), treatment.getDrug().getInternationalName());
        assertEquals(treatmentDto.getDrugDto().getTradeName(), treatment.getDrug().getTradeName());
        assertEquals(treatmentDto.getDrugDto().getActiveSubstance(), treatment.getDrug().getActiveSubstance());
        assertEquals(treatmentDto.getDrugDto().getBrand(), treatment.getDrug().getBrand());
        assertEquals(treatmentDto.getDrugDto().getDosage(), treatment.getDrug().getDosage());
        assertEquals(treatmentDto.getDrugDto().getLeafletUrl(), treatment.getDrug().getLeafletUrl());

        //cleanup
        userRepository.deleteById(user.getId());
    }

    @Test
    void shouldMapToUserTreatmentFromCreated() throws UserNotFoundException, DrugNotFoundException {
        //given
        User user = new User("user", "Krakow",
                "user@email.com", "password");
        userRepository.save(user);
        CreatedUserTreatmentDto treatmentDto = getCreatedUserTreatmentDto();

        //when
        UserTreatment treatment = mapper.mapToUserTreatment(treatmentDto);

        //then
        assertEquals(treatmentDto.getId(), treatment.getId());
        assertEquals(treatmentDto.getUsername(), treatment.getUser().getUsername());
        assertEquals(treatmentDto.getStartedAt(), treatment.getStartedAt());
        assertEquals(treatmentDto.getFinishedAt(), treatment.getFinishedAt());
        assertEquals(treatmentDto.getDrugDto().getId(), treatment.getDrug().getId());
        assertEquals(treatmentDto.getDrugDto().getUniqueDrugId(), treatment.getDrug().getUniqueDrugId());
        assertEquals(treatmentDto.getDrugDto().getInternationalName(), treatment.getDrug().getInternationalName());
        assertEquals(treatmentDto.getDrugDto().getTradeName(), treatment.getDrug().getTradeName());
        assertEquals(treatmentDto.getDrugDto().getActiveSubstance(), treatment.getDrug().getActiveSubstance());
        assertEquals(treatmentDto.getDrugDto().getBrand(), treatment.getDrug().getBrand());
        assertEquals(treatmentDto.getDrugDto().getDosage(), treatment.getDrug().getDosage());
        assertEquals(treatmentDto.getDrugDto().getLeafletUrl(), treatment.getDrug().getLeafletUrl());

        //cleanup
        userRepository.deleteById(user.getId());
    }

    @Test
    void mapToCreatedUserTreatmentDto() {
        //given
        User user = new User("user", "Krakow",
                "user@email.com", "password");
        userRepository.save(user);
        UserTreatment treatment = getUserTreatment();
        treatment.setUser(user);

        //when
        CreatedUserTreatmentDto treatmentDto = mapper.mapToCreatedUserTreatmentDto(treatment);

        //then
        assertEquals(treatment.getId(), treatmentDto.getId());
        assertEquals(treatment.getUser().getUsername(), treatmentDto.getUsername());
        assertEquals(treatment.getStartedAt(), treatmentDto.getStartedAt());
        assertEquals(treatment.getFinishedAt(), treatmentDto.getFinishedAt());
        assertEquals(treatment.getDrug().getId(), treatmentDto.getDrugDto().getId());
        assertEquals(treatment.getDrug().getUniqueDrugId(), treatmentDto.getDrugDto().getUniqueDrugId());
        assertEquals(treatment.getDrug().getInternationalName(), treatmentDto.getDrugDto().getInternationalName());
        assertEquals(treatment.getDrug().getTradeName(), treatmentDto.getDrugDto().getTradeName());
        assertEquals(treatment.getDrug().getActiveSubstance(), treatmentDto.getDrugDto().getActiveSubstance());
        assertEquals(treatment.getDrug().getBrand(), treatmentDto.getDrugDto().getBrand());
        assertEquals(treatment.getDrug().getDosage(), treatmentDto.getDrugDto().getDosage());
        assertEquals(treatment.getDrug().getLeafletUrl(), treatmentDto.getDrugDto().getLeafletUrl());

        //cleanup
        userRepository.deleteById(user.getId());

    }

    @Test
    void mapToCreatedUserTreatmentDtoList() {
        //given
        User user = new User("user", "Krakow",
                "user@email.com", "password");
        userRepository.save(user);
        UserTreatment treatment = getUserTreatment();
        treatment.setUser(user);
        List<UserTreatment> treatments = List.of(treatment);

        //when
        List<CreatedUserTreatmentDto> treatmentDtos = mapper.mapToCreatedUserTreatmentDtoList(treatments);

        //then
        assertEquals(treatments.size(), treatmentDtos.size());
        assertEquals(treatments.get(0).getId(), treatmentDtos.get(0).getId());
        assertEquals(treatments.get(0).getUser().getUsername(), treatmentDtos.get(0).getUsername());
        assertEquals(treatments.get(0).getStartedAt(), treatmentDtos.get(0).getStartedAt());
        assertEquals(treatments.get(0).getFinishedAt(), treatmentDtos.get(0).getFinishedAt());
        assertEquals(treatments.get(0).getDrug().getId(), treatmentDtos.get(0).getDrugDto().getId());
        assertEquals(treatments.get(0).getDrug().getUniqueDrugId(), treatmentDtos.get(0).getDrugDto().getUniqueDrugId());
        assertEquals(treatments.get(0).getDrug().getInternationalName(), treatmentDtos.get(0).getDrugDto().getInternationalName());
        assertEquals(treatments.get(0).getDrug().getTradeName(), treatmentDtos.get(0).getDrugDto().getTradeName());
        assertEquals(treatments.get(0).getDrug().getActiveSubstance(), treatmentDtos.get(0).getDrugDto().getActiveSubstance());
        assertEquals(treatments.get(0).getDrug().getBrand(), treatmentDtos.get(0).getDrugDto().getBrand());
        assertEquals(treatments.get(0).getDrug().getDosage(), treatmentDtos.get(0).getDrugDto().getDosage());
        assertEquals(treatments.get(0).getDrug().getLeafletUrl(), treatmentDtos.get(0).getDrugDto().getLeafletUrl());

        //cleanup
        userRepository.deleteById(user.getId());

    }


    private UserTreatment getUserTreatment(){
        UserTreatment treatment = new UserTreatment();
        treatment.setId(1L);
        treatment.setDrug(createDrug());
        treatment.setStartedAt(LocalDate.now());
        treatment.setFinishedAt(LocalDate.now().plusDays(5));
        return treatment;
    }

    private UserTreatmentDto getUserTreatmentDto(){
        UserTreatmentDto dto = new UserTreatmentDto();
        dto.setDrugDto(createDrugDto());
        dto.setUsername("user");
        dto.setStartedAt(LocalDate.now());
        dto.setFinishedAt(LocalDate.now().plusDays(5));
        return dto;
    }

    private CreatedUserTreatmentDto getCreatedUserTreatmentDto(){
        CreatedUserTreatmentDto dto = new CreatedUserTreatmentDto();
        dto.setId(1L);
        dto.setDrugDto(createDrugDto());
        dto.setUsername("user");
        dto.setStartedAt(LocalDate.now());
        dto.setFinishedAt(LocalDate.now().plusDays(5));
        return dto;
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