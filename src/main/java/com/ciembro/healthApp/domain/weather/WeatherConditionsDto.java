package com.ciembro.healthApp.domain.weather;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class WeatherConditionsDto {

    private long id;
    private LocalDateTime checkDate;
    private String location;
    private double temp;
    private double tempFeelsLike;
    private String weatherText;
    private String iconUrl;
    private int pressure;
    private double windKph;
    private int humidity;
}
