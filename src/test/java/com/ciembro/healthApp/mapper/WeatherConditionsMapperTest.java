package com.ciembro.healthApp.mapper;

import com.ciembro.healthApp.domain.weather.WeatherConditions;
import com.ciembro.healthApp.domain.weather.WeatherConditionsDto;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
@RunWith(SpringRunner.class)
@SpringBootTest()
class WeatherConditionsMapperTest {

    @Autowired
    private WeatherConditionsMapper weatherMapper;

    @Test
    void mapToWeatherConditionsDto() {
        //given
        WeatherConditions weather = getWeatherConditions();
        //when
        WeatherConditionsDto dto = weatherMapper.mapToWeatherConditionsDto(weather);
        //then
        assertEquals(weather.getId(), dto.getId());
        assertEquals(weather.getWeatherText(), dto.getWeatherText());
        assertEquals(weather.getCheckDate(), dto.getCheckDate());
        assertEquals(weather.getIconUrl(),dto.getIconUrl());
        assertEquals(weather.getTemp(), dto.getTemp());
        assertEquals(weather.getTempFeelsLike(), dto.getTempFeelsLike());
        assertEquals(weather.getPressure(), dto.getPressure());
        assertEquals(weather.getHumidity(), dto.getHumidity());
        assertEquals(weather.getWindKph(), dto.getWindKph());
        assertEquals(weather.getLocation(), dto.getLocation());
    }

    @Test
    void mapToWeatherConditions() {

        //given
        WeatherConditionsDto dto = getWeatherConditionsDto();
        //when
        WeatherConditions weather = weatherMapper.mapToWeatherConditions(dto);
        //then
        assertEquals(dto.getId(), weather.getId());
        assertEquals(dto.getWeatherText(), weather.getWeatherText());
        assertEquals(dto.getCheckDate(), weather.getCheckDate());
        assertEquals(dto.getIconUrl(),weather.getIconUrl());
        assertEquals(dto.getTemp(), weather.getTemp());
        assertEquals(dto.getTempFeelsLike(), weather.getTempFeelsLike());
        assertEquals(dto.getPressure(), weather.getPressure());
        assertEquals(dto.getHumidity(), weather.getHumidity());
        assertEquals(dto.getWindKph(), weather.getWindKph());
        assertEquals(dto.getLocation(), weather.getLocation());
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