package com.ciembro.healthApp.mapper;

import com.ciembro.healthApp.domain.SideEffect;
import com.ciembro.healthApp.domain.SideEffectDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SideEffectMapper {

    public SideEffectDto mapToSideEffectDto(SideEffect sideEffect){
        SideEffectDto dto = new SideEffectDto();
        dto.setId(sideEffect.getId());
        dto.setText(sideEffect.getText());
        return dto;
    }

    public SideEffect mapToSideEffect(SideEffectDto dto){
        SideEffect sideEffect = new SideEffect();
        sideEffect.setId(dto.getId());
        sideEffect.setText(dto.getText());
        return sideEffect;    }

    public List<SideEffectDto> mapToSideEffectDtoList(List<SideEffect> sideEffects){
        return sideEffects.stream()
                .map(this::mapToSideEffectDto)
                .collect(Collectors.toList());
    }
}
