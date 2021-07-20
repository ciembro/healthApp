package com.ciembro.healthApp.controller;

import com.ciembro.healthApp.domain.*;
import com.ciembro.healthApp.domain.user.AuthenticationRequest;
import com.ciembro.healthApp.domain.user.User;
import com.ciembro.healthApp.mapper.SideEffectMapper;
import com.ciembro.healthApp.repository.UserRepository;
import com.ciembro.healthApp.service.SideEffectService;
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

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitWebConfig
@AutoConfigureMockMvc
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SideEffectsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @MockBean
    private SideEffectService effectService;

    @MockBean
    private SideEffectMapper effectMapper;

    private String token;
    private User user;

    @Test
    void shouldReturnSideEffectsList() throws Exception {
        //given
        SideEffect sideEffect =  new SideEffect();
        sideEffect.setId(1L);
        sideEffect.setText("Ból głowy");
        SideEffectDto dto =  new SideEffectDto();
        dto.setId(1L);
        dto.setText("Ból głowy");
        List<SideEffectDto> effectDtos = List.of(dto);
        List<SideEffect> effects = List.of(sideEffect);

        when(effectService.findAll()).thenReturn(effects);
        when(effectMapper.mapToSideEffectDtoList(anyList())).thenReturn(effectDtos);

        //when&then
        mockMvc.perform(MockMvcRequestBuilders
                .get("/v1/effects")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].text", Matchers.is("Ból głowy")));
    }

    @Test
    void shouldReturnEmptySideEffectsList() throws Exception {
        //given
        List<SideEffectDto> effectDtos = Arrays.asList();
        List<SideEffect> effects = Arrays.asList();

        when(effectService.findAll()).thenReturn(effects);
        when(effectMapper.mapToSideEffectDtoList(anyList())).thenReturn(effectDtos);

        //when&then
        mockMvc.perform(MockMvcRequestBuilders
                .get("/v1/effects")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(0)));
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