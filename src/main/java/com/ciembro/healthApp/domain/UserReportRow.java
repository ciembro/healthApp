package com.ciembro.healthApp.domain;

import com.ciembro.healthApp.domain.drug.Drug;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserReportRow {

    private Insights insights;
    private List<Drug> drugs;

}
