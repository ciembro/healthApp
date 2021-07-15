package com.ciembro.healthApp.service;

import com.ciembro.healthApp.client.WeatherApiClient;
import com.ciembro.healthApp.domain.weather.api.WeatherApiDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherApiService {

    @Autowired
    private WeatherApiClient client;

    public WeatherApiDto getWeatherForLocation(String city){
        return client.getWeatherForLocation(city);
    }
}
