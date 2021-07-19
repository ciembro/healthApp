package com.ciembro.healthApp.domain;

import com.ciembro.healthApp.domain.drug.DrugDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserReportRowDto {

    private CreatedInsightsDto insights;
    private List<DrugDto> drugs;

}
