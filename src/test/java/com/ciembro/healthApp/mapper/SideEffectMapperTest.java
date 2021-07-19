package com.ciembro.healthApp.mapper;

import com.ciembro.healthApp.domain.EmotionalState;
import com.ciembro.healthApp.domain.EmotionalStateDto;
import com.ciembro.healthApp.domain.SideEffect;
import com.ciembro.healthApp.domain.SideEffectDto;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest()
class SideEffectMapperTest {

    @Autowired
    private SideEffectMapper mapper;

    @Test
    void shouldMapToSideEffect(){
        //given
        SideEffect sideEffect = getSideEffect();
        //when
        SideEffectDto dto = mapper.mapToSideEffectDto(sideEffect);
        //then
        assertEquals(sideEffect.getId(), dto.getId());
        assertEquals(sideEffect.getText(), dto.getText());

    }

    @Test
    void shouldMapToSideEffectDto(){
        //given
        SideEffectDto dto = getSideEffectDto();
        //when
        SideEffect sideEffect = mapper.mapToSideEffect(dto);
        //then
        assertEquals(dto.getId(), sideEffect.getId());
        assertEquals(dto.getText(), sideEffect.getText());

    }

    @Test
    void shouldMapToSideEffectDtoList(){
        //given
        List<SideEffect> sideEffects = List.of(getSideEffect());

        //when
        List<SideEffectDto> dtos = mapper.mapToSideEffectDtoList(sideEffects);

        //then
        assertEquals(sideEffects.size(), dtos.size());
        assertEquals(sideEffects.get(0).getId(), dtos.get(0).getId());
        assertEquals(sideEffects.get(0).getText(), dtos.get(0).getText());
    }

    private SideEffectDto getSideEffectDto(){
        SideEffectDto sideEffectDto =  new SideEffectDto();
        sideEffectDto.setId(1L);
        sideEffectDto.setText("Ból głowy");
        return sideEffectDto;
    }

    private SideEffect getSideEffect(){
        SideEffect sideEffect =  new SideEffect();
        sideEffect.setId(1L);
        sideEffect.setText("Ból głowy");
        return sideEffect;
    }


}