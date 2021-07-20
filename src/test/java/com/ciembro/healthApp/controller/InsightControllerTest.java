package com.ciembro.healthApp.controller;

import com.ciembro.healthApp.LocalDateAdapter;
import com.ciembro.healthApp.LocalDateTimeAdapter;
import com.ciembro.healthApp.domain.*;
import com.ciembro.healthApp.domain.user.AuthenticationRequest;
import com.ciembro.healthApp.domain.user.User;
import com.ciembro.healthApp.domain.weather.WeatherConditionsDto;
import com.ciembro.healthApp.facade.InsightsFacade;
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
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitWebConfig
@AutoConfigureMockMvc
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class InsightControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @MockBean
    private InsightsFacade insightsFacade;

    private String token;
    private User user;

    @Test
    void shouldCreateInsights() throws Exception {
        //given
        CreatedInsightsDto createdInsightsDto = getCreatedInsightsDto();
        InsightsDto insightsDto = getInsightsDto();
        when(insightsFacade.createInsights(any(InsightsDto.class))).thenReturn(createdInsightsDto);

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();

        String jsonContent = gson.toJson(insightsDto);
        //when&then
        mockMvc.perform(MockMvcRequestBuilders
                .post("/v1/insights")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username", Matchers.is("user")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.creationDate", Matchers.is(LocalDate.now().toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.comment", Matchers.is("comment")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.emotions", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.sideEffects", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.weather.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.weather.temp", Matchers.is(25.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.weather.tempFeelsLike", Matchers.is(25.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.weather.pressure", Matchers.is(1000)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.weather.windKph", Matchers.is(5.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.weather.weatherText", Matchers.is("weather text")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.weather.iconUrl", Matchers.is("icon/url")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.weather.location", Matchers.is("Krakow")));
    }

    @Test
    void updateInsights() throws Exception {
        //given
        CreatedInsightsDto updatedInsightDto = getCreatedInsightsDto();
        updatedInsightDto.setComment("changed comment");
        updatedInsightDto.setEmotions(Set.of());

        when(insightsFacade.updateInsights(any(CreatedInsightsDto.class))).thenReturn(updatedInsightDto);

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();

        String jsonContent = gson.toJson(updatedInsightDto);
        //when&then
        mockMvc.perform(MockMvcRequestBuilders
                .put("/v1/insights")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.username", Matchers.is("user")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.creationDate", Matchers.is(LocalDate.now().toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.comment", Matchers.is("changed comment")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.emotions", Matchers.hasSize(0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.sideEffects", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.weather.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.weather.temp", Matchers.is(25.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.weather.tempFeelsLike", Matchers.is(25.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.weather.pressure", Matchers.is(1000)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.weather.windKph", Matchers.is(5.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.weather.weatherText", Matchers.is("weather text")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.weather.iconUrl", Matchers.is("icon/url")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.weather.location", Matchers.is("Krakow")));
    }

    @Test
    void deleteInsights() throws Exception {
        CreatedInsightsDto dtoToDelete = getCreatedInsightsDto();
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .create();

        String jsonContent = gson.toJson(dtoToDelete);

        //when&then
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/v1/insights")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void shouldReturnAllUserInsights() throws Exception {
        //given
        List<CreatedInsightsDto> insightsDtos = Arrays.asList(getCreatedInsightsDto());

        when(insightsFacade.getAllUserInsights(anyString())).thenReturn(insightsDtos);

        //when&then
        mockMvc.perform(MockMvcRequestBuilders
                .get("/v1/insights/all")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].username", Matchers.is("user")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].creationDate", Matchers.is(LocalDate.now().toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].comment", Matchers.is("comment")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].emotions", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].sideEffects", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].weather.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].weather.temp", Matchers.is(25.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].weather.tempFeelsLike", Matchers.is(25.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].weather.pressure", Matchers.is(1000)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].weather.windKph", Matchers.is(5.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].weather.weatherText", Matchers.is("weather text")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].weather.iconUrl", Matchers.is("icon/url")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].weather.location", Matchers.is("Krakow")));

    }

    private InsightsDto getInsightsDto(){
        EmotionalStateDto emotionDto =  new EmotionalStateDto();
        emotionDto.setId(1L);
        emotionDto.setEngText("JOY");
        emotionDto.setPlText("Radość");

        SideEffectDto sideEffectDto =  new SideEffectDto();
        sideEffectDto.setId(1L);
        sideEffectDto.setText("Ból głowy");

        InsightsDto insightsDto = new InsightsDto();
        insightsDto.setUsername(user.getUsername());
        insightsDto.setComment("comment");
        insightsDto.setEmotions(Set.of(emotionDto));
        insightsDto.setSideEffects(Set.of(sideEffectDto));
        return insightsDto;
    }

    private CreatedInsightsDto getCreatedInsightsDto(){
        EmotionalStateDto emotionDto =  new EmotionalStateDto();
        emotionDto.setId(1L);
        emotionDto.setEngText("JOY");
        emotionDto.setPlText("Radość");

        SideEffectDto sideEffectDto =  new SideEffectDto();
        sideEffectDto.setId(1L);
        sideEffectDto.setText("Ból głowy");

        return CreatedInsightsDto.builder()
                .id(1L)
                .creationDate(LocalDate.now())
                .username(user.getUsername())
                .sideEffects(Set.of(sideEffectDto))
                .emotions(Set.of(emotionDto))
                .comment("comment")
                .weather(getWeatherConditionsDto())
                .build();
    }




    private WeatherConditionsDto getWeatherConditionsDto(){
        return WeatherConditionsDto.builder()
                .id(1L)
                .checkDate(LocalDateTime.of(2021,7,19,15,0,0))
                .humidity(1)
                .iconUrl("icon/url")
                .location("Krakow")
                .pressure(1000)
                .temp(25.0)
                .tempFeelsLike(25.0)
                .weatherText("weather text")
                .windKph(5)
                .build();
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