package com.ciembro.healthApp.domain.sideeffect;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SideEffectToAddDto {

    private String username;
    private long drugId;
    private String details;
}
