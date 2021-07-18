package com.ciembro.healthApp.controller;

import com.ciembro.healthApp.domain.SideEffect;
import com.ciembro.healthApp.domain.SideEffectDto;
import com.ciembro.healthApp.mapper.SideEffectMapper;
import com.ciembro.healthApp.service.SideEffectService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/effects")
@RequiredArgsConstructor
public class SideEffectsController {

    private final SideEffectService service;
    private final SideEffectMapper mapper;

    @GetMapping
    public List<SideEffectDto> getAvailableSideEffectValues(){
        List<SideEffect> sideEffects = service.findAll();
        return mapper.mapToSideEffectDtoList(sideEffects);
    }
}
