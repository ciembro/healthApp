package com.ciembro.healthApp.mapper;

import com.ciembro.healthApp.domain.EmotionalState;
import com.ciembro.healthApp.domain.EmotionalStateDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmotionalStateMapper {

    public EmotionalStateDto mapToEmotionalStateDto(EmotionalState emotion){
        EmotionalStateDto dto = new EmotionalStateDto();
        dto.setId(emotion.getId());
        dto.setEngText(emotion.getEngText());
        dto.setPlText(emotion.getPlText());
        return dto;
    }

    public EmotionalState mapToEmotionalState(EmotionalStateDto dto){
        EmotionalState emotionalState = new EmotionalState();
        emotionalState.setId(dto.getId());
        emotionalState.setEngText(dto.getEngText());
        emotionalState.setPlText(dto.getPlText());
        return emotionalState;
    }

    public List<EmotionalStateDto> mapToEmotionalStatesDtoList(List<EmotionalState> emotions){
        return emotions.stream()
                .map(this::mapToEmotionalStateDto)
                .collect(Collectors.toList());
    }
}
