package com.ciembro.healthApp.client;

import com.ciembro.healthApp.config.WeatherApiConfig;
import com.ciembro.healthApp.domain.weather.api.WeatherApiDto;
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
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class WeatherApiClient {

    private final RestTemplate restTemplate;
    private final WeatherApiConfig weatherConfig;

    private final String WEATHER_URL = "/current.json";


    private HttpHeaders getHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.set("x-rapidapi-key", weatherConfig.getWeatherApiKey());
        headers.set("x-rapidapi-host", weatherConfig.getWeatherApiHost());

        return headers;
    }

    public WeatherApiDto getWeatherForLocation(String city){
        try {
            HttpEntity<String> entity = new HttpEntity<>(getHeaders());

            ResponseEntity<WeatherApiDto> response = restTemplate.exchange(getUriForLocation(city),
                    HttpMethod.GET,
                    entity,
                    WeatherApiDto.class);

            WeatherApiDto weather = response.getBody();
            if (weather != null){
                return weather;
            }

        } catch (RestClientException e){
            System.out.println(e.getMessage());
        }
        return null;
    }


    private URI getUriForLocation(String city){
        return UriComponentsBuilder.fromHttpUrl(weatherConfig.getWeatherApiEndpoint() + WEATHER_URL)
                .queryParam("q", city)
                .queryParam("lang", "pl")
                .build()
                .encode()
                .toUri();
    }


}
