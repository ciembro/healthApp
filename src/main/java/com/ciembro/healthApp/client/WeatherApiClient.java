package com.ciembro.healthApp.client;

import com.ciembro.healthApp.config.WeatherApiConfig;
import com.ciembro.healthApp.domain.weather.api.LocationApiDto;
import com.ciembro.healthApp.domain.weather.api.WeatherConditionsApiDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class WeatherApiClient {

    private final RestTemplate restTemplate;
    private final WeatherApiConfig weatherConfig;

    private final String GET_WEATHER_URL = "/current.json";
    private final String GET_LOCATIONS_URL = "/search.json";


    private HttpHeaders getHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-rapidapi-key", weatherConfig.getWeatherApiKey());
        headers.set("x-rapidapi-host", weatherConfig.getWeatherApiHost());

        return headers;
    }

    public WeatherConditionsApiDto getWeatherForLocation(String location){
        try {
            HttpEntity<String> entity = new HttpEntity<>(getHeaders());
            ResponseEntity<WeatherConditionsApiDto> response = restTemplate.exchange(getUriForWeather(location),
                    HttpMethod.GET,
                    entity,
                    WeatherConditionsApiDto.class);

            WeatherConditionsApiDto weather = response.getBody();
            if (weather != null){
                return weather;
            }

        } catch (RestClientException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<LocationApiDto> searchForLocations(String location){
        try {
            HttpEntity<String> entity = new HttpEntity<>(getHeaders());
            ResponseEntity<LocationApiDto[]> response = restTemplate.exchange(getUriForLocations(location),
                    HttpMethod.GET,
                    entity,
                    LocationApiDto[].class);

            LocationApiDto[] locations = response.getBody();

            return Optional.ofNullable(locations)
                    .map(Arrays::asList)
                    .orElse(Collections.emptyList())
                    .stream()
                    .filter(loc -> loc.getCountry().equals("Poland"))
                    .collect(Collectors.toList());
        } catch (RestClientException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    private URI getUriForWeather(String location){
        return UriComponentsBuilder.fromHttpUrl(weatherConfig.getWeatherApiEndpoint() + GET_WEATHER_URL)
                .queryParam("q", location)
                .queryParam("lang", "pl")
                .build()
                .encode()
                .toUri();
    }

    private URI getUriForLocations(String location){
        return UriComponentsBuilder.fromHttpUrl(weatherConfig.getWeatherApiEndpoint() + GET_LOCATIONS_URL)
                .queryParam("q", location)
                .build()
                .encode()
                .toUri();
    }


}
