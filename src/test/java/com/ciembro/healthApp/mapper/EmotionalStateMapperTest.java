package com.ciembro.healthApp.mapper;

import com.ciembro.healthApp.domain.EmotionalState;
import com.ciembro.healthApp.domain.EmotionalStateDto;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest()
class EmotionalStateMapperTest {

    @Autowired
    private EmotionalStateMapper mapper;

    @Test
    void shouldMapToEmotionalState(){
        //given
        EmotionalState emotionalState = getEmotionalState();
        //when
        EmotionalStateDto dto = mapper.mapToEmotionalStateDto(emotionalState);
        //then
        assertEquals(emotionalState.getId(), dto.getId());
        assertEquals(emotionalState.getEngText(), dto.getEngText());
        assertEquals(emotionalState.getPlText(), dto.getPlText());
    }

    @Test
    void shouldMapToEmotionalStateDto(){
        //given
        EmotionalStateDto dto = getEmotionalStateDto();
        //when
        EmotionalState emotionalState = mapper.mapToEmotionalState(dto);
        //then
        assertEquals(dto.getId(), emotionalState.getId());
        assertEquals(dto.getEngText(), emotionalState.getEngText());
        assertEquals(dto.getPlText(), emotionalState.getPlText());
    }

    @Test
    void shouldMapToEmotionalStateDtoList(){
        //given
        List<EmotionalState> emotionalStates = List.of(getEmotionalState());

        //when
        List<EmotionalStateDto> dtos = mapper.mapToEmotionalStatesDtoList(emotionalStates);

        //then
        assertEquals(emotionalStates.size(), dtos.size());
        assertEquals(emotionalStates.get(0).getId(), dtos.get(0).getId());
        assertEquals(emotionalStates.get(0).getEngText(), dtos.get(0).getEngText());
        assertEquals(emotionalStates.get(0).getPlText(), dtos.get(0).getPlText());
    }

    private EmotionalState getEmotionalState(){
        EmotionalState emotion =  new EmotionalState();
        emotion.setId(1L);
        emotion.setEngText("JOY");
        emotion.setPlText("Radość");
        return emotion;
    }

    private EmotionalStateDto getEmotionalStateDto(){
        EmotionalStateDto dto =  new EmotionalStateDto();
        dto.setId(1L);
        dto.setEngText("JOY");
        dto.setPlText("Radość");
        return dto;
    }

}