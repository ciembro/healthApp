package com.ciembro.healthApp.service;

import com.ciembro.healthApp.client.WeatherApiClient;
import com.ciembro.healthApp.domain.weather.api.WeatherConditionsApiDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherApiService {

    @Autowired
    private WeatherApiClient client;

    public WeatherConditionsApiDto getWeatherForLocation(String city){
        return client.getWeatherForLocation(city);
    }
}
