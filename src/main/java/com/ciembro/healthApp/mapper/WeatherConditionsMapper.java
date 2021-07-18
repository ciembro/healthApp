package com.ciembro.healthApp.mapper;

import com.ciembro.healthApp.domain.weather.WeatherConditions;
import com.ciembro.healthApp.domain.weather.WeatherConditionsDto;
import com.ciembro.healthApp.domain.weather.api.WeatherConditionsApiDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeatherConditionsMapper {

    public WeatherConditions mapToWeather(WeatherConditionsApiDto weatherDto){
        WeatherConditions weather = new WeatherConditions();
        weather.setLocation(weatherDto.getLocation().getCity());
        weather.setWeatherText(weatherDto.getCurrent().getCondition().getText());
        weather.setIconUrl(weatherDto.getCurrent().getCondition().getIconUrl());
        weather.setTemp(weatherDto.getCurrent().getTemp());
        weather.setTempFeelsLike(weatherDto.getCurrent().getTempFeelsLike());
        weather.setHumidity(weatherDto.getCurrent().getHumidity());
        weather.setPressure(weatherDto.getCurrent().getPressure());
        weather.setWindKph(weatherDto.getCurrent().getWindKph());

        return weather;
    }

    public WeatherConditionsDto mapToWeatherConditionsDto(WeatherConditions weather){
        WeatherConditionsDto dto = new WeatherConditionsDto();
        dto.setId(weather.getId());
        dto.setLocation(weather.getLocation());
        dto.setWeatherText(weather.getWeatherText());
        dto.setIconUrl(weather.getIconUrl());
        dto.setTemp(weather.getTemp());
        dto.setTempFeelsLike(weather.getTempFeelsLike());
        dto.setHumidity(weather.getHumidity());
        dto.setPressure(weather.getPressure());
        dto.setWindKph(weather.getWindKph());
        return dto;
    }

    public WeatherConditions mapToWeatherConditions(WeatherConditionsDto weatherDto){
        WeatherConditions weather = new WeatherConditions();
        weather.setId(weatherDto.getId());
        weather.setLocation(weatherDto.getLocation());
        weather.setWeatherText(weatherDto.getWeatherText());
        weather.setIconUrl(weatherDto.getIconUrl());
        weather.setTemp(weatherDto.getTemp());
        weather.setTempFeelsLike(weatherDto.getTempFeelsLike());
        weather.setHumidity(weatherDto.getHumidity());
        weather.setPressure(weatherDto.getPressure());
        weather.setWindKph(weatherDto.getWindKph());
        return weather;
    }
}
