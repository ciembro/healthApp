package com.ciembro.healthApp.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class InsightsDto {

    private String username;
    private Set<EmotionalStateDto> emotions;
    private Set<SideEffectDto> sideEffects;
    private String comment;

}
