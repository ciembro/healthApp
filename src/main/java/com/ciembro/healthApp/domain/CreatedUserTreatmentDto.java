package com.ciembro.healthApp.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class CreatedUserTreatmentDto {

    private long id;
    private String username;
    private long drugId;
    private LocalDate startedAt;
    private LocalDate finishedAt;
}
