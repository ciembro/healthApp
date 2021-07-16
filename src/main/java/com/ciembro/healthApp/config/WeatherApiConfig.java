package com.ciembro.healthApp.config;


import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@NoArgsConstructor
public class WeatherApiConfig {

    @Value("${x-rapidapi-key}")
    private String weatherApiKey;

    @Value("${x-rapidapi-host}")
    private String weatherApiHost;

    @Value("${weather.api.endpoint}")
    private String weatherApiEndpoint;
}
