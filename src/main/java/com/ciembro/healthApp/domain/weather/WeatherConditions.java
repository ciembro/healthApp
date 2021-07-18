package com.ciembro.healthApp.domain.weather;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
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

}
