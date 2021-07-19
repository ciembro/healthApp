package com.ciembro.healthApp.domain.weather;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Entity
public class WeatherConditions {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @CreationTimestamp
    @Column
    private LocalDateTime checkDate;

    @Column
    private String location;

    @Column(precision = 5, scale = 2)
    private double temp;

    @Column(precision = 5, scale = 2)
    private double tempFeelsLike;

    @Column
    private String weatherText;

    @Column
    private String iconUrl;

    @Column
    private int pressure;

    @Column(precision = 5, scale = 2)
    private double windKph;

    @Column
    private int humidity;

    public static WeatherBuilder builder(){
        return new WeatherBuilder();
    }

    public static class WeatherBuilder {
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

        public WeatherBuilder id(long id) {
            this.id = id;
            return this;
        }

        public WeatherBuilder checkDate(LocalDateTime checkDate) {
            this.checkDate = checkDate;
            return this;
        }

        public WeatherBuilder location(String location) {
            this.location = location;
            return this;
        }

        public WeatherBuilder temp(double temp) {
            this.temp = temp;
            return this;
        }

        public WeatherBuilder tempFeelsLike(double tempFeelsLike) {
            this.tempFeelsLike = tempFeelsLike;
            return this;
        }

        public WeatherBuilder weatherText(String weatherText) {
            this.weatherText = weatherText;
            return this;
        }

        public WeatherBuilder iconUrl(String iconUrl) {
            this.iconUrl = iconUrl;
            return this;
        }

        public WeatherBuilder pressure(int pressure) {
            this.pressure = pressure;
            return this;
        }

        public WeatherBuilder windKph(double windKph) {
            this.windKph = windKph;
            return this;
        }

        public WeatherBuilder humidity(int humidity) {
            this.humidity = humidity;
            return this;
        }

        public WeatherConditions build(){
            return new WeatherConditions(id, checkDate, location,  temp, tempFeelsLike,
                    weatherText, iconUrl, pressure, windKph, humidity);
        }
    }

}
