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
        return WeatherConditions.builder()
                .location(weatherDto.getLocation().getCity())
                .weatherText(weatherDto.getCurrent().getCondition().getText())
                .humidity(weatherDto.getCurrent().getHumidity())
                .iconUrl(weatherDto.getCurrent().getCondition().getIconUrl())
                .temp(weatherDto.getCurrent().getTemp())
                .tempFeelsLike(weatherDto.getCurrent().getTempFeelsLike())
                .pressure(weatherDto.getCurrent().getPressure())
                .windKph(weatherDto.getCurrent().getWindKph())
                .build();
    }

    public WeatherConditionsDto mapToWeatherConditionsDto(WeatherConditions weather){
        return WeatherConditionsDto.builder()
                .id(weather.getId())
                .checkDate(weather.getCheckDate())
                .location(weather.getLocation())
                .weatherText(weather.getWeatherText())
                .humidity(weather.getHumidity())
                .iconUrl(weather.getIconUrl())
                .temp(weather.getTemp())
                .tempFeelsLike(weather.getTempFeelsLike())
                .pressure(weather.getPressure())
                .windKph(weather.getWindKph())
                .build();
    }

    public WeatherConditions mapToWeatherConditions(WeatherConditionsDto weatherDto){
        return WeatherConditions.builder()
                .id(weatherDto.getId())
                .checkDate(weatherDto.getCheckDate())
                .location(weatherDto.getLocation())
                .weatherText(weatherDto.getWeatherText())
                .humidity(weatherDto.getHumidity())
                .iconUrl(weatherDto.getIconUrl())
                .temp(weatherDto.getTemp())
                .tempFeelsLike(weatherDto.getTempFeelsLike())
                .pressure(weatherDto.getPressure())
                .windKph(weatherDto.getWindKph())
                .build();
    }
}
