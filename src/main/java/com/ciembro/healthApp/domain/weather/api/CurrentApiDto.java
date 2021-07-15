package com.ciembro.healthApp.domain.weather.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrentApiDto {

    @JsonProperty("temp_c")
    private double temp;

    @JsonProperty("feelslike_c")
    private double tempFeelsLike;

    @JsonProperty("condition")
    private ConditionApiDto condition;

    @JsonProperty("pressure_mb")
    private int pressure;

    @JsonProperty("wind_kph")
    private double windKph;

    @JsonProperty("humidity")
    private int humidity;

}
