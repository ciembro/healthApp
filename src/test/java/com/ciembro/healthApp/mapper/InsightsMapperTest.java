package com.ciembro.healthApp.mapper;

import com.ciembro.healthApp.domain.*;
import com.ciembro.healthApp.domain.user.User;
import com.ciembro.healthApp.domain.weather.WeatherConditions;
import com.ciembro.healthApp.domain.weather.WeatherConditionsDto;
import com.ciembro.healthApp.exception.UserNotFoundException;
import com.ciembro.healthApp.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest()
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class InsightsMapperTest {

    @Autowired
    private InsightsMapper insightsMapper;

    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    void before(){
        user = new User("user", "Krakow","user@email.com", "password");
        user = userRepository.save(user);
    }

    @AfterEach
    void after(){
        userRepository.deleteById(user.getId());
    }

    @Test
    void shouldMapToInsightFromInsightsDto() throws UserNotFoundException {
        //given
        InsightsDto dto = getInsightsDto();
        //when
        Insights insight = insightsMapper.mapToInsight(dto);

        //then
        assertEquals(dto.getUsername(), insight.getUser().getUsername());
        assertEquals(dto.getEmotions().size(), insight.getEmotions().size());
        assertEquals(dto.getSideEffects().size(), insight.getSideEffects().size());
        assertEquals(dto.getComment(), insight.getComment());

    }

    @Test
    void shouldMapToInsightFromCreatedInsights() throws UserNotFoundException {
        //given
        CreatedInsightsDto dto = getCreatedInsightsDto();
        //when
        Insights insight = insightsMapper.mapToInsight(dto);

        //then
        assertEquals(dto.getId(), insight.getId());
        assertEquals(dto.getUsername(), insight.getUser().getUsername());
        assertEquals(dto.getComment(), insight.getComment());
        assertEquals(dto.getCreationDate(), insight.getCreationDate());
        assertEquals(dto.getEmotions().size(), insight.getEmotions().size());
        assertEquals(dto.getSideEffects().size(), insight.getSideEffects().size());
        assertEquals(dto.getWeather().getId(), insight.getWeather().getId());
        assertEquals(dto.getWeather().getWeatherText(), insight.getWeather().getWeatherText());
        assertEquals(dto.getWeather().getCheckDate(), insight.getWeather().getCheckDate());
        assertEquals(dto.getWeather().getIconUrl(),insight.getWeather().getIconUrl());
        assertEquals(dto.getWeather().getTemp(), insight.getWeather().getTemp());
        assertEquals(dto.getWeather().getTempFeelsLike(), insight.getWeather().getTempFeelsLike());
        assertEquals(dto.getWeather().getPressure(), insight.getWeather().getPressure());
        assertEquals(dto.getWeather().getHumidity(), insight.getWeather().getHumidity());
        assertEquals(dto.getWeather().getWindKph(), insight.getWeather().getWindKph());
        assertEquals(dto.getWeather().getLocation(), insight.getWeather().getLocation());
    }

    @Test
    void shouldMapToCreatedInsightDto() {
        //given
        Insights insights = getInsights();
        //when
        CreatedInsightsDto dto = insightsMapper.mapToCreatedInsightDto(insights);

        //then
        assertEquals(insights.getId(), dto.getId());
        assertEquals(insights.getUser().getUsername(), dto.getUsername());
        assertEquals(insights.getComment(), dto.getComment());
        assertEquals(insights.getCreationDate(), dto.getCreationDate());
        assertEquals(insights.getEmotions().size(), dto.getEmotions().size());
        assertEquals(insights.getSideEffects().size(), dto.getSideEffects().size());
        assertEquals(insights.getWeather().getId(), dto.getWeather().getId());
        assertEquals(insights.getWeather().getWeatherText(), dto.getWeather().getWeatherText());
        assertEquals(insights.getWeather().getCheckDate(), dto.getWeather().getCheckDate());
        assertEquals(insights.getWeather().getIconUrl(),dto.getWeather().getIconUrl());
        assertEquals(insights.getWeather().getTemp(), dto.getWeather().getTemp());
        assertEquals(insights.getWeather().getTempFeelsLike(), dto.getWeather().getTempFeelsLike());
        assertEquals(insights.getWeather().getPressure(), dto.getWeather().getPressure());
        assertEquals(insights.getWeather().getHumidity(), dto.getWeather().getHumidity());
        assertEquals(insights.getWeather().getWindKph(), dto.getWeather().getWindKph());
        assertEquals(insights.getWeather().getLocation(), dto.getWeather().getLocation());
    }

    @Test
    void shouldMapToCreatedInsightDtoList() {
        //given
        List<Insights> insightsList = List.of(getInsights());
        //when
        List<CreatedInsightsDto> dtos = insightsMapper.mapToCreatedInsightDtoList(insightsList);

        //then
        assertEquals(insightsList.get(0).getId(), dtos.get(0).getId());
        assertEquals(insightsList.get(0).getUser().getUsername(), dtos.get(0).getUsername());
        assertEquals(insightsList.get(0).getComment(), dtos.get(0).getComment());
        assertEquals(insightsList.get(0).getCreationDate(), dtos.get(0).getCreationDate());
        assertEquals(insightsList.get(0).getEmotions().size(), dtos.get(0).getEmotions().size());
        assertEquals(insightsList.get(0).getSideEffects().size(), dtos.get(0).getSideEffects().size());
        assertEquals(insightsList.get(0).getWeather().getId(), dtos.get(0).getWeather().getId());
        assertEquals(insightsList.get(0).getWeather().getWeatherText(), dtos.get(0).getWeather().getWeatherText());
        assertEquals(insightsList.get(0).getWeather().getCheckDate(), dtos.get(0).getWeather().getCheckDate());
        assertEquals(insightsList.get(0).getWeather().getIconUrl(),dtos.get(0).getWeather().getIconUrl());
        assertEquals(insightsList.get(0).getWeather().getTemp(), dtos.get(0).getWeather().getTemp());
        assertEquals(insightsList.get(0).getWeather().getTempFeelsLike(), dtos.get(0).getWeather().getTempFeelsLike());
        assertEquals(insightsList.get(0).getWeather().getPressure(), dtos.get(0).getWeather().getPressure());
        assertEquals(insightsList.get(0).getWeather().getHumidity(), dtos.get(0).getWeather().getHumidity());
        assertEquals(insightsList.get(0).getWeather().getWindKph(), dtos.get(0).getWeather().getWindKph());
        assertEquals(insightsList.get(0).getWeather().getLocation(), dtos.get(0).getWeather().getLocation());
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


    private Insights getInsights(){
        EmotionalState emotion =  new EmotionalState();
        emotion.setId(1L);
        emotion.setEngText("JOY");
        emotion.setPlText("Radość");

        SideEffect sideEffect =  new SideEffect();
        sideEffect.setId(1L);
        sideEffect.setText("Ból głowy");

        return Insights.builder()
                .id(1L)
                .creationDate(LocalDate.now())
                .user(user)
                .sideEffects(List.of(sideEffect))
                .emotions(List.of(emotion))
                .comment("comment")
                .weather(getWeatherConditions())
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

    private WeatherConditions getWeatherConditions(){
        return WeatherConditions.builder()
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
}