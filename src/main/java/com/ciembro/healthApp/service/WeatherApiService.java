package com.ciembro.healthApp.service;

import com.ciembro.healthApp.client.WeatherApiClient;
import com.ciembro.healthApp.domain.weather.WeatherConditions;
import com.ciembro.healthApp.domain.weather.api.LocationApiDto;
import com.ciembro.healthApp.mapper.LocationMapper;
import com.ciembro.healthApp.mapper.WeatherConditionsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WeatherApiService {

    @Autowired
    private WeatherApiClient client;

    @Autowired
    private LocationMapper locationMapper;

    @Autowired
    private WeatherConditionsMapper weatherMapper;

    public WeatherConditions getWeatherForLocation(String city){
        return weatherMapper.mapToWeather(client.getWeatherForLocation(city));
    }

    public List<String> searchForLocations(String location){
        List<LocationApiDto> locations = client.searchForLocations(location);
        return locationMapper.mapToCityList(locations);
    }
}
