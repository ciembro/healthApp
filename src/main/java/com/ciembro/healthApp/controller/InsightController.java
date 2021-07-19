package com.ciembro.healthApp.controller;

import com.ciembro.healthApp.domain.CreatedInsightsDto;
import com.ciembro.healthApp.domain.InsightsDto;
import com.ciembro.healthApp.exception.UserNotFoundException;
import com.ciembro.healthApp.exception.WeatherConditionsNotFoundException;
import com.ciembro.healthApp.facade.InsightsFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/v1/insights")
@RequiredArgsConstructor
public class InsightController {

    private final InsightsFacade facade;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public CreatedInsightsDto createInsights(@RequestBody InsightsDto insightsDto)
            throws UserNotFoundException {

        return facade.createInsights(insightsDto);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public CreatedInsightsDto updateInsights(@RequestBody CreatedInsightsDto insightsDto) throws UserNotFoundException, WeatherConditionsNotFoundException {

        return facade.updateInsights(insightsDto);
    }

    @DeleteMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void deleteInsights(@RequestBody CreatedInsightsDto insightsDto) throws UserNotFoundException, WeatherConditionsNotFoundException {
        facade.deleteInsights(insightsDto);
    }


    @GetMapping("/all")
    public List<CreatedInsightsDto> getAllUserInsights(Authentication authentication)
            throws UserNotFoundException {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return facade.getAllUserInsights(userDetails.getUsername());
    }

}
