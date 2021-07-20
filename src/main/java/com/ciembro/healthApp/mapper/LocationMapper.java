package com.ciembro.healthApp.mapper;

import com.ciembro.healthApp.domain.weather.api.LocationApiDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LocationMapper {

    public List<String> mapToCityList(List<LocationApiDto> locations){
        return locations.stream()
                .map(LocationApiDto::getCity)
                .collect(Collectors.toList());
    }
}
