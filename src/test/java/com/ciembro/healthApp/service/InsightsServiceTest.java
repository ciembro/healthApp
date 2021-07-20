package com.ciembro.healthApp.service;

import com.ciembro.healthApp.domain.EmotionalState;
import com.ciembro.healthApp.domain.Insights;
import com.ciembro.healthApp.domain.SideEffect;
import com.ciembro.healthApp.domain.user.User;
import com.ciembro.healthApp.domain.weather.WeatherConditions;
import com.ciembro.healthApp.repository.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest()
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class InsightsServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WeatherConditionsRepository weatherConditionsRepository;

    @Autowired
    private InsightsRepository insightsRepository;

    @Autowired
    private EmotionalStateRepository emotionalStateRepository;

    @Autowired
    private SideEffectRepository sideEffectRepository;

    @Autowired
    InsightsService insightsService;


    @Test
    void save() {
    }

    @Test
    void getAllInsightsByUserId() {
        //given
        User user = new User("user", "Krakow","user@email.com", "password");
        user = userRepository.save(user);
        WeatherConditions weatherConditions = getWeatherConditions();
        weatherConditions = weatherConditionsRepository.save(weatherConditions);
        SideEffect sideEffect = getSideEffect();
        sideEffect = sideEffectRepository.save(sideEffect);
        EmotionalState emotionalState = getEmotion();
        emotionalState = emotionalStateRepository.save(emotionalState);


        Insights insights = Insights.builder()
                .user(user)
                .weather(weatherConditions)
                .creationDate(LocalDate.now())
                .sideEffects(List.of(sideEffect))
                .emotions(List.of(emotionalState))
                .comment("comment")
                .build();

        insightsRepository.save(insights);

        //when
        List<Insights> insightsList = insightsService.getAllInsightsByUserId(user.getId());

        //then
        assertEquals(1, insightsList.size());
        assertEquals(user.getId(), insightsList.get(0).getUser().getId());
        assertEquals(weatherConditions.getId(), insightsList.get(0).getWeather().getId());

        //cleanup
        insightsRepository.deleteById(insights.getId());
        sideEffectRepository.deleteById(sideEffect.getId());
        emotionalStateRepository.deleteById(emotionalState.getId());
        weatherConditionsRepository.deleteById(weatherConditions.getId());
        userRepository.deleteById(user.getId());
    }

    @Test
    void delete() {
    }

    private WeatherConditions getWeatherConditions(){
        return WeatherConditions.builder()
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

    private EmotionalState getEmotion(){
        EmotionalState emotion =  new EmotionalState();
        emotion.setEngText("JOY");
        emotion.setPlText("Radość");
        return emotion;
    }

    private SideEffect getSideEffect(){
        SideEffect sideEffect =  new SideEffect();
        sideEffect.setText("Ból głowy");
        return sideEffect;

    }

}