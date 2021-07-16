package com.ciembro.healthApp.mapper;

import com.ciembro.healthApp.domain.EmotionalState;
import com.ciembro.healthApp.domain.EmotionalStateDto;
import org.springframework.stereotype.Service;

@Service
public class EmotionalStateMapper {

    public EmotionalStateDto mapToEmotionalStateDto(EmotionalState emotion){
        EmotionalStateDto dto = new EmotionalStateDto();
        dto.setId(emotion.getId());
        dto.setPlText(emotion.getPlText());
        return dto;
    }

    public EmotionalState mapToEmotionalState(EmotionalStateDto dto){
        EmotionalState emotionalState = new EmotionalState();
        emotionalState.setId(dto.getId());
        emotionalState.setPlText(dto.getPlText());
        return emotionalState;
    }
}
