package com.ciembro.healthApp.facade;

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
        return weatherApiService.searchForLocations(location);
    }
}
