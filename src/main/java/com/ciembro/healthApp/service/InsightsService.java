package com.ciembro.healthApp.service;

import com.ciembro.healthApp.domain.EmotionalState;
import com.ciembro.healthApp.domain.Insights;
import com.ciembro.healthApp.domain.SideEffect;
import com.ciembro.healthApp.exception.InsightsNotFoundException;
import com.ciembro.healthApp.repository.InsightsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InsightsService {

    @Autowired
    private InsightsRepository insightsRepository;

    @Autowired
    private SideEffectService sideEffectService;

    @Autowired
    private EmotionalStateService emotionalStateService;

    @Autowired
    private WeatherConditionsService weatherService;

    public Insights save(Insights insights){

        for (EmotionalState emotion : insights.getEmotions()){
            emotion.getInsights().add(insights);
            emotionalStateService.save(emotion);
        }

        for (SideEffect sideEffect : insights.getSideEffects()){
            sideEffect.getInsights().add(insights);
            sideEffectService.save(sideEffect);
        }

        return insightsRepository.save(insights);
    }

    public List<Insights> getAllInsightsByUserId(long userId){
        return  insightsRepository.getAllInsightsByUserId(userId);
    }

    public void delete(Insights insights) {
        for (EmotionalState emotion : insights.getEmotions()){
            emotion.getInsights().remove(insights);
            emotionalStateService.save(emotion);
        }

        for (SideEffect sideEffect : insights.getSideEffects()){
            sideEffect.getInsights().remove(insights);
            sideEffectService.save(sideEffect);
        }

        insightsRepository.deleteById(insights.getId());
        weatherService.deleteById(insights.getWeather().getId());
    }


}


