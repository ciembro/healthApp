package com.ciembro.healthApp.controller;

import com.ciembro.healthApp.domain.weather.api.WeatherApiDto;
import com.ciembro.healthApp.service.DrugApiService;
import com.ciembro.healthApp.service.WeatherApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/admin")
@RequiredArgsConstructor
public class AdminController {

    private final DrugApiService drugApiService;
    private final WeatherApiService weatherApiService;

    @GetMapping("/drugs")
    public void loadToDb(){
        drugApiService.updateDrugList();
    }

    @GetMapping("/conditions/{city}")
    public void loadConditions(@PathVariable String city){

       WeatherApiDto dto = weatherApiService.getWeatherForLocation(city);
        System.out.println(dto);
    }
}
