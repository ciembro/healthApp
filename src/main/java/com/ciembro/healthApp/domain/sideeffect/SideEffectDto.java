package com.ciembro.healthApp.domain.sideeffect;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SideEffectDto {

    private long id;
    private String username;
    private long drugId;
    private LocalDate creationDate;
    private String details;
}
