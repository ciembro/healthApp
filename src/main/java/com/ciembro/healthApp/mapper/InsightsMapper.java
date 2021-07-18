package com.ciembro.healthApp.mapper;

import com.ciembro.healthApp.domain.*;
import com.ciembro.healthApp.exception.UserNotFoundException;
import com.ciembro.healthApp.exception.WeatherConditionsNotFoundException;
import com.ciembro.healthApp.service.UserService;
import com.ciembro.healthApp.service.WeatherConditionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class InsightsMapper {

    @Autowired
    private WeatherConditionsMapper weatherMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private EmotionalStateMapper emotionalStateMapper;

    @Autowired
    private SideEffectMapper sideEffectMapper;

    public Insights mapToInsight(CreatedInsightsDto dto)
            throws WeatherConditionsNotFoundException, UserNotFoundException {

        List<EmotionalState> emotions = dto.getEmotions().stream()
                .map(e -> emotionalStateMapper.mapToEmotionalState(e))
                .collect(Collectors.toList());

        List<SideEffect> effects = dto.getSideEffects().stream()
                .map(e -> sideEffectMapper.mapToSideEffect(e))
                .collect(Collectors.toList());

        return Insights.builder()
                .id(dto.getId())
                .weather(weatherMapper.mapToWeatherConditions(dto.getWeather()))
                .user(userService.findByUsername(dto.getUsername()))
                .creationDate(dto.getCreationDate())
                .emotions(emotions)
                .sideEffects(effects)
                .comment(dto.getComment())
                .build();
    }

    public Insights mapToInsight(InsightsDto dto) throws UserNotFoundException {

        List<EmotionalState> emotions = dto.getEmotions().stream()
                .map(e -> emotionalStateMapper.mapToEmotionalState(e))
                .collect(Collectors.toList());

        List<SideEffect> effects = dto.getSideEffects().stream()
                .map(e -> sideEffectMapper.mapToSideEffect(e))
                .collect(Collectors.toList());

        return Insights.builder()
                .user(userService.findByUsername(dto.getUsername()))
                .emotions(emotions)
                .sideEffects(effects)
                .comment(dto.getComment())
                .build();
    }

    public CreatedInsightsDto mapToCreatedInsightDto(Insights insights){

        Set<EmotionalStateDto> emotions = insights.getEmotions().stream()
                .map(e -> emotionalStateMapper.mapToEmotionalStateDto(e))
                .collect(Collectors.toSet());

        Set<SideEffectDto> effects = insights.getSideEffects().stream()
                .map(e -> sideEffectMapper.mapToSideEffectDto(e))
                .collect(Collectors.toSet());

        return CreatedInsightsDto.InsightsDtoBuilder.builder()
                .id(insights.getId())
                .username(insights.getUser().getUsername())
                .weather(weatherMapper.mapToWeatherConditionsDto(insights.getWeather()))
                .creationDate(insights.getCreationDate())
                .emotions(emotions)
                .sideEffects(effects)
                .comment(insights.getComment())
                .build();
    }

    public List<CreatedInsightsDto> mapToCreatedInsightDtoList(List<Insights> insights){
        return  insights.stream()
                .map(this::mapToCreatedInsightDto)
                .collect(Collectors.toList());
    }

}
