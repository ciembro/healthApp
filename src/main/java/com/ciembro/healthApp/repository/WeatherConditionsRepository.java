package com.ciembro.healthApp.repository;

import com.ciembro.healthApp.domain.weather.WeatherConditions;
import org.springframework.data.repository.CrudRepository;

public interface WeatherConditionsRepository extends CrudRepository<WeatherConditions, Long> {

}
