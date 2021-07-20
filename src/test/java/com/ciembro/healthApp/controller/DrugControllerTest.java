package com.ciembro.healthApp.controller;

import com.ciembro.healthApp.domain.user.AuthenticationRequest;
import com.ciembro.healthApp.domain.drug.Drug;
import com.ciembro.healthApp.domain.drug.DrugDto;
import com.ciembro.healthApp.domain.user.User;
import com.ciembro.healthApp.facade.DrugFacade;
import com.ciembro.healthApp.mapper.DrugMapper;
import com.ciembro.healthApp.repository.UserRepository;
import com.google.gson.Gson;
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

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitWebConfig
@AutoConfigureMockMvc
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DrugControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @MockBean
    private  DrugMapper drugMapper;

    @MockBean
    private DrugFacade drugFacade;

    private String token;
    private User user;

    @Test
    void shouldReturnTextMatches() throws Exception {

        //given
        DrugDto drugDto = createDrugDto();
        Drug drug = createDrug();

        when(drugMapper.mapToDrugDto(drug)).thenReturn(drugDto);
        when(drugFacade.searchMatchingDrugs(anyString())).thenReturn(Arrays.asList(drugDto));

        //when&then
        mockMvc.perform(MockMvcRequestBuilders
                .get("/v1/drugs/search/intern")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].uniqueDrugId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].internationalName", Matchers.is("international name")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].tradeName", Matchers.is("trade name")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].activeSubstance", Matchers.is("active substance")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].brand", Matchers.is("brand")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].dosage", Matchers.is("dosage")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].leafletUrl", Matchers.is("http://test.com")));
    }

    @Test
    void shouldReturnNoTextMatches() throws Exception {
        //given
        when(drugFacade.searchMatchingDrugs(anyString())).thenReturn(Arrays.asList());

        //when&then
        mockMvc.perform(MockMvcRequestBuilders
                .get("/v1/drugs/search/abc")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
    }

    @Test
    void shouldReturnDrugByDate() throws Exception {

        //given
        DrugDto drugDto = createDrugDto();
        List<DrugDto> drugDtoList = Arrays.asList(drugDto);

        when(drugFacade.getDrugByDate(LocalDate.now(), "user")).thenReturn(drugDtoList);

        //when&then
        mockMvc.perform(MockMvcRequestBuilders
                .get("/v1/drugs/" + LocalDate.now())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].uniqueDrugId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].internationalName", Matchers.is("international name")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].tradeName", Matchers.is("trade name")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].activeSubstance", Matchers.is("active substance")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].brand", Matchers.is("brand")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].dosage", Matchers.is("dosage")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].leafletUrl", Matchers.is("http://test.com")));
    }

    @Test
    void shouldReturnDrugListForUser() throws Exception {
        //given
        DrugDto drugDto = createDrugDto();
        List<DrugDto> drugDtoList = Arrays.asList(drugDto);

        when(drugFacade.getAllByUser("user")).thenReturn(drugDtoList);

        //when&then
        mockMvc.perform(MockMvcRequestBuilders
                .get("/v1/drugs/user")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].uniqueDrugId", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].internationalName", Matchers.is("international name")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].tradeName", Matchers.is("trade name")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].activeSubstance", Matchers.is("active substance")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].brand", Matchers.is("brand")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].dosage", Matchers.is("dosage")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].leafletUrl", Matchers.is("http://test.com")));
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



}