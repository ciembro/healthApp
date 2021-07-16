package com.ciembro.healthApp.controller;

import com.ciembro.healthApp.facade.WeatherConditionsFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/weather")
@RequiredArgsConstructor
public class WeatherConditionsController {

    private final WeatherConditionsFacade facade;

    @GetMapping("/loc")
    public List<String> searchForMatchingLocations(String location){
        return facade.searchForMatchingLocations(location);
    }


}
