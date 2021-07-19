package com.ciembro.healthApp.domain.weather;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
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

    public static WeatherDtoBuilder builder(){
        return new WeatherDtoBuilder();
    }

    public static class WeatherDtoBuilder {
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

        public WeatherDtoBuilder id(long id) {
            this.id = id;
            return this;
        }

        public WeatherDtoBuilder checkDate(LocalDateTime checkDate) {
            this.checkDate = checkDate;
            return this;
        }

        public WeatherDtoBuilder location(String location) {
            this.location = location;
            return this;
        }

        public WeatherDtoBuilder temp(double temp) {
            this.temp = temp;
            return this;
        }

        public WeatherDtoBuilder tempFeelsLike(double tempFeelsLike) {
            this.tempFeelsLike = tempFeelsLike;
            return this;
        }

        public WeatherDtoBuilder weatherText(String weatherText) {
            this.weatherText = weatherText;
            return this;
        }

        public WeatherDtoBuilder iconUrl(String iconUrl) {
            this.iconUrl = iconUrl;
            return this;
        }

        public WeatherDtoBuilder pressure(int pressure) {
            this.pressure = pressure;
            return this;
        }

        public WeatherDtoBuilder windKph(double windKph) {
            this.windKph = windKph;
            return this;
        }

        public WeatherDtoBuilder humidity(int humidity) {
            this.humidity = humidity;
            return this;
        }

        public WeatherConditionsDto build(){
            return new WeatherConditionsDto(id, checkDate, location,  temp, tempFeelsLike,
                    weatherText, iconUrl, pressure, windKph, humidity);
        }
    }
}
