package com.ciembro.healthApp.mapper;

import com.ciembro.healthApp.domain.weather.WeatherConditions;
import com.ciembro.healthApp.domain.weather.api.WeatherApiDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WeatherMapper {

    public WeatherConditions mapToWeather(WeatherApiDto weatherDto){
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
}
