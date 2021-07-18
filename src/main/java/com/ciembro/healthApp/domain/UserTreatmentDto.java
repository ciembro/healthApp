package com.ciembro.healthApp.domain;

import com.ciembro.healthApp.domain.drug.DrugDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class UserTreatmentDto {

    private String username;
    private DrugDto drugDto;
    private LocalDate startedAt;
    private LocalDate finishedAt;
}
