package com.ciembro.healthApp.domain.weather;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class WeatherDto {

    private long id;
    private LocalDate currentDate;
    private String location;
    private double temp;
    private double tempFeelsLike;
    private String weatherText;
    private String iconUrl;
    private int pressure;
    private double windKph;
    private int humidity;
}
