package com.ciembro.healthApp.controller;

import com.ciembro.healthApp.domain.weather.WeatherConditionsDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/v1/weather")
public class WeatherConditionsController {



    @GetMapping("/loc")
    public List<String> searchForMatchingLocations(){

        return new ArrayList<>();
    }

    @GetMapping()
    public WeatherConditionsDto getWeatherForLocation(){

        return new WeatherConditionsDto();
    }

}
