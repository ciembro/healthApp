package com.ciembro.healthApp.controller;

import com.ciembro.healthApp.LocalDateAdapter;
import com.ciembro.healthApp.domain.AuthenticationRequest;
import com.ciembro.healthApp.domain.CreatedUserTreatmentDto;
import com.ciembro.healthApp.domain.UserTreatmentDto;
import com.ciembro.healthApp.domain.drug.Drug;
import com.ciembro.healthApp.domain.drug.DrugDto;
import com.ciembro.healthApp.domain.user.User;
import com.ciembro.healthApp.facade.UserTreatmentFacade;
import com.ciembro.healthApp.repository.UserRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringJUnitWebConfig
@AutoConfigureMockMvc
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserTreatmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @MockBean
    private UserTreatmentFacade userTreatmentFacade;

    private String token;
    private User user;

    @Test
    void shouldCreateUserTreatment() throws Exception {
        //given
        UserTreatmentDto userTreatmentDto = getUserTreatmentDto();
        CreatedUserTreatmentDto createdUserTreatmentDto = getCreatedUserTreatmentDto();
        when(userTreatmentFacade.createUserTreatment(userTreatmentDto)).thenReturn(createdUserTreatmentDto);

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();

        String jsonContent = gson.toJson(userTreatmentDto);

        //when&then
        mockMvc.perform(MockMvcRequestBuilders
                .post("/v1/treatment")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username", Matchers.is("user")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.drugDto.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.drugDto.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.drugDto.uniqueDrugId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.drugDto.internationalName", Matchers.is("international name")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.drugDto.tradeName", Matchers.is("trade name")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.drugDto.activeSubstance", Matchers.is("active substance")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.drugDto.brand", Matchers.is("brand")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.drugDto.dosage", Matchers.is("dosage")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.drugDto.leafletUrl", Matchers.is("http://test.com")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.startedAt", Matchers.is(LocalDate.now().toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.finishedAt", Matchers.is(LocalDate.now().plusDays(5).toString())));
    }

    @Test
    void shouldUpdateUserTreatment() throws Exception {
        //given
        CreatedUserTreatmentDto dtoToUpdate = getCreatedUserTreatmentDto();
        dtoToUpdate.setFinishedAt(LocalDate.now().plusDays(10));
        CreatedUserTreatmentDto updatedDto = getCreatedUserTreatmentDto();
        updatedDto.setFinishedAt(LocalDate.now().plusDays(10));

        when(userTreatmentFacade.updateUserTreatment(any(CreatedUserTreatmentDto.class))).thenReturn(updatedDto);

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();

        String jsonContent = gson.toJson(dtoToUpdate);

        //when&then
        mockMvc.perform(MockMvcRequestBuilders
                .put("/v1/treatment")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username", Matchers.is("user")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.drugDto.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.drugDto.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.drugDto.uniqueDrugId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.drugDto.internationalName", Matchers.is("international name")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.drugDto.tradeName", Matchers.is("trade name")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.drugDto.activeSubstance", Matchers.is("active substance")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.drugDto.brand", Matchers.is("brand")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.drugDto.dosage", Matchers.is("dosage")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.drugDto.leafletUrl", Matchers.is("http://test.com")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.startedAt", Matchers.is(LocalDate.now().toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.finishedAt", Matchers.is(LocalDate.now().plusDays(10).toString())));
    }

    @Test
    void shouldDeleteUserTreatment() throws Exception {
        //given
        CreatedUserTreatmentDto dtoToDelete = getCreatedUserTreatmentDto();
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();

        String jsonContent = gson.toJson(dtoToDelete);

        //when&then
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/v1/treatment")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void shouldReturnAllUserTreatments() throws Exception {
        //given
        List<CreatedUserTreatmentDto> treatmentDtos = Arrays.asList(getCreatedUserTreatmentDto());

        when(userTreatmentFacade.getAllUserTreatments(anyString())).thenReturn(treatmentDtos);

        //when&then
        mockMvc.perform(MockMvcRequestBuilders
                .get("/v1/treatment")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].username", Matchers.is("user")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].drugDto.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].drugDto.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].drugDto.uniqueDrugId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].drugDto.internationalName", Matchers.is("international name")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].drugDto.tradeName", Matchers.is("trade name")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].drugDto.activeSubstance", Matchers.is("active substance")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].drugDto.brand", Matchers.is("brand")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].drugDto.dosage", Matchers.is("dosage")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].drugDto.leafletUrl", Matchers.is("http://test.com")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].startedAt", Matchers.is(LocalDate.now().toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].finishedAt", Matchers.is(LocalDate.now().plusDays(5).toString())));
    }

    @BeforeAll
    void auth() throws Exception {
        String password = passwordEncoder.encode("pass");
        user = new User("user", "Krakow","user@email.com", password);
        user = userRepository.save(user);

        Gson gson = new Gson();
        AuthenticationRequest authenticationRequest = new AuthenticationRequest("user", "pass");
        String authRequest = gson.toJson(authenticationRequest);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/v1/authenticate")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(authRequest))
                .andExpect(status().isOk()).andReturn();

        String authResponse = result.getResponse().getContentAsString();
        String[] responseTab = authResponse.split(",");
        String responseToken = responseTab[0].substring(8);
        token = responseToken.replace("\"", "");
    }

    @AfterAll
    void cleanup(){
        userRepository.deleteById(user.getId());
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

}
