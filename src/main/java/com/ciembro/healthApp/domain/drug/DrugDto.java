package com.ciembro.healthApp.domain.drug;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DrugDto {

    private long id;
    private String commonName;
    private String tradeName;
    private String dose;
    private String brand;
    private String activeSubstance;
    private String leafletUrl;

}