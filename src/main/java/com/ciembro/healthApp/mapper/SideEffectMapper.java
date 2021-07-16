package com.ciembro.healthApp.mapper;

import com.ciembro.healthApp.domain.SideEffectDto;
import com.ciembro.healthApp.domain.SideEffect;
import com.ciembro.healthApp.repository.SideEffectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
