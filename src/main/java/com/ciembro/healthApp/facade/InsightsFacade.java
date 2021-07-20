package com.ciembro.healthApp.facade;

import com.ciembro.healthApp.domain.CreatedInsightsDto;
import com.ciembro.healthApp.domain.Insights;
import com.ciembro.healthApp.domain.InsightsDto;
import com.ciembro.healthApp.domain.user.User;
import com.ciembro.healthApp.domain.weather.WeatherConditions;
import com.ciembro.healthApp.exception.UserNotFoundException;
import com.ciembro.healthApp.exception.WeatherConditionsNotFoundException;
import com.ciembro.healthApp.mapper.InsightsMapper;
import com.ciembro.healthApp.service.InsightsService;
import com.ciembro.healthApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Component
public class InsightsFacade {

    @Autowired
    private InsightsMapper insightsMapper;

    @Autowired
    private InsightsService insightsService;

    @Autowired
    private UserService userService;

    @Autowired
    private WeatherConditionsFacade weatherFacade;

    public CreatedInsightsDto createInsights(@RequestBody InsightsDto insightsDto) throws UserNotFoundException {
        Insights insights = insightsMapper.mapToInsight(insightsDto);
        WeatherConditions weatherConditions = weatherFacade.getWeatherForLocation(insights.getUser().getLocation());
        weatherConditions = weatherFacade.save(weatherConditions);
        insights.setWeather(weatherConditions);
        return insightsMapper.mapToCreatedInsightDto(insightsService.save(insights));
    }

    public CreatedInsightsDto updateInsights(@RequestBody CreatedInsightsDto insightsDto) throws UserNotFoundException, WeatherConditionsNotFoundException {
        Insights insights = insightsMapper.mapToInsight(insightsDto);
        return insightsMapper.mapToCreatedInsightDto(insightsService.save(insights));
    }

    public void deleteInsights(@RequestBody CreatedInsightsDto insightsDto)
            throws UserNotFoundException {
        Insights insights = insightsMapper.mapToInsight(insightsDto);
        insightsService.delete(insights);
    }

    public List<CreatedInsightsDto> getAllUserInsights(String username) throws UserNotFoundException {
        User user = userService.findByUsername(username);
        return insightsMapper.mapToCreatedInsightDtoList(insightsService
                .getAllInsightsByUserId(user.getId()));
    }
}
