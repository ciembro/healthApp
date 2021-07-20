package com.ciembro.healthApp.controller;

import com.ciembro.healthApp.domain.user.AuthenticationRequest;
import com.ciembro.healthApp.domain.EmotionalState;
import com.ciembro.healthApp.domain.EmotionalStateDto;
import com.ciembro.healthApp.domain.user.User;
import com.ciembro.healthApp.mapper.EmotionalStateMapper;
import com.ciembro.healthApp.repository.UserRepository;
import com.ciembro.healthApp.service.EmotionalStateService;
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
class EmotionalStateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @MockBean
    private  EmotionalStateService emotionalStateService;

    @MockBean
    private EmotionalStateMapper emotionalStateMapper;

    private String token;
    private User user;

    @Test
    void shouldReturnEmotionsList() throws Exception {
        //given
        EmotionalState emotion =  new EmotionalState();
        emotion.setId(1L);
        emotion.setEngText("JOY");
        emotion.setPlText("Radość");
        EmotionalStateDto dto =  new EmotionalStateDto();
        dto.setId(1L);
        dto.setEngText("JOY");
        dto.setPlText("Radość");
        List<EmotionalStateDto> emotionDtos = Arrays.asList(dto);
        List<EmotionalState> emotions = Arrays.asList(emotion);

        when(emotionalStateService.findAll()).thenReturn(emotions);
        when(emotionalStateMapper.mapToEmotionalStatesDtoList(anyList())).thenReturn(emotionDtos);

        //when&then
        mockMvc.perform(MockMvcRequestBuilders
                .get("/v1/emotions")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .characterEncoding("UTF-8")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].plText", Matchers.is("Radość")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].engText", Matchers.is("JOY")));
    }

    @Test
    void shouldReturnEmptyList() throws Exception {
        //given
        List<EmotionalStateDto> emotionDtos = Arrays.asList();
        List<EmotionalState> emotions = Arrays.asList();

        when(emotionalStateService.findAll()).thenReturn(emotions);
        when(emotionalStateMapper.mapToEmotionalStatesDtoList(anyList())).thenReturn(emotionDtos);

        //when&then
        mockMvc.perform(MockMvcRequestBuilders
                .get("/v1/emotions")
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