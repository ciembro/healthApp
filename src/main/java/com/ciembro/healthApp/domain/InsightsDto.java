package com.ciembro.healthApp.domain;

import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
public class InsightsDto {

    private String username;
    private Set<EmotionalStateDto> emotions;
    private Set<SideEffectDto> sideEffects;
    private String comment;

}
