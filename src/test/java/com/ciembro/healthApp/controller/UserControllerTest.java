package com.ciembro.healthApp.controller;

import com.ciembro.healthApp.domain.AuthenticationRequest;
import com.ciembro.healthApp.domain.user.User;
import com.ciembro.healthApp.facade.DrugFacade;
import com.ciembro.healthApp.facade.UserFacade;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringJUnitWebConfig
@AutoConfigureMockMvc
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private String token;
    private User user;

    @Test
    void shouldChangeUserLocation() throws Exception {
        //given
        //when&then
        mockMvc.perform(MockMvcRequestBuilders
                .put("/v1/user/" + user.getUsername() + "/Poznan")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

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
}