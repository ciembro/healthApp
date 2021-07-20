package com.ciembro.healthApp.facade;

import com.ciembro.healthApp.domain.weather.WeatherConditions;
import com.ciembro.healthApp.service.WeatherApiService;
import com.ciembro.healthApp.service.WeatherConditionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WeatherConditionsFacade {

    @Autowired
    private WeatherApiService weatherApiService;

    @Autowired
    private WeatherConditionsService weatherConditionsService;

    public List<String> searchForMatchingLocations(String location){
        List<String> s = weatherApiService.searchForLocations(location);
        s.forEach(System.out::println);
        return s;
    }

    public WeatherConditions getWeatherForLocation(String location){
        return weatherApiService.getWeatherForLocation(location);
    }

    public WeatherConditions save(WeatherConditions weatherConditions){
        return weatherConditionsService.save(weatherConditions);
    }
}
