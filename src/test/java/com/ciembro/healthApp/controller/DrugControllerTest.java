package com.ciembro.healthApp.controller;

import com.ciembro.healthApp.domain.AuthenticationRequest;
import com.ciembro.healthApp.domain.drug.Drug;
import com.ciembro.healthApp.domain.drug.DrugDto;
import com.ciembro.healthApp.domain.user.User;
import com.ciembro.healthApp.exception.DrugNotFoundException;
import com.ciembro.healthApp.mapper.DrugMapper;
import com.ciembro.healthApp.repository.UserRepository;
import com.google.gson.Gson;
import org.junit.jupiter.api.*;
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

    private String token;
    private User user;


    @BeforeAll
    void auth() throws Exception {
        String password = passwordEncoder.encode("pass");
        user = new User("user", "user@email.com", password);
        user = userRepository.save(user);

        Gson gson = new Gson();
        AuthenticationRequest authenticationRequest = new AuthenticationRequest("user", "pass");
        String authRequest = gson.toJson(authenticationRequest);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/v1/authenticate")
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(authRequest))
                .andExpect(status().isOk()).andReturn();

        String response = result.getResponse().getContentAsString();
        response = response.substring(8);
        token = response.replace("\"}", "");
    }

    @AfterAll
    void cleanup(){
        userRepository.deleteById(user.getId());
    }

    @Test
    void shouldAddDrugToUserList() throws Exception {
        //given
        DrugDto drugDto = createDrugDto();
        Drug drug = createDrug();

        when(drugMapper.mapToDrug(drugDto)).thenReturn(drug);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(drugDto);

        //when&then
        mockMvc.perform(MockMvcRequestBuilders
                .post("/v1/drugs")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
    }

    @Test
    void shouldRemoveDrugFromUserList() throws DrugNotFoundException {
        //given
        DrugDto drugDto = createDrugDto();
        Drug drug = createDrug();

        when(drugMapper.mapToDrug(drugDto)).thenReturn(drug);
    }



    private Drug createDrug(){
        Drug drug = new Drug();
        drug.setId(1L);
        drug.setInternationalName("common name");
        drug.setTradeName("tradeName");
        drug.setDosage("dose");
        drug.setBrand("brand");
        drug.setActiveSubstance("active substance");
        drug.setLeafletUrl("http://test.com");
        return drug;
    }

    private DrugDto createDrugDto(){
        return new DrugDto (1L,
                1,
                "common name",
                "trade name",
                "dose",
                "brand",
                "active substance",
                "http://test.com");
    }

}