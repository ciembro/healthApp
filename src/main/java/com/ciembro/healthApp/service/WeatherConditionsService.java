package com.ciembro.healthApp.service;

import com.ciembro.healthApp.domain.weather.WeatherConditions;
import com.ciembro.healthApp.exception.WeatherConditionsNotFoundException;
import com.ciembro.healthApp.repository.WeatherConditionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherConditionsService {

    @Autowired
    private WeatherConditionsRepository repository;

    public WeatherConditions save(WeatherConditions weatherConditions){
        return repository.save(weatherConditions);
    }

    public void deleteById(long weatherId){
        repository.deleteById(weatherId);
    }

}
