package com.ciembro.healthApp.controller;

import com.ciembro.healthApp.domain.EmotionalState;
import com.ciembro.healthApp.domain.EmotionalStateDto;
import com.ciembro.healthApp.mapper.EmotionalStateMapper;
import com.ciembro.healthApp.service.EmotionalStateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/emotions")
@RequiredArgsConstructor
public class EmotionalStateController {

    private final EmotionalStateService service;
    private final EmotionalStateMapper mapper;

    @GetMapping
    public List<EmotionalStateDto> getAvailableEmotionsValues(){
        List<EmotionalState> emotionalStates = service.findAll();
        return mapper.mapToEmotionalStatesDtoList(emotionalStates);
    }
}
