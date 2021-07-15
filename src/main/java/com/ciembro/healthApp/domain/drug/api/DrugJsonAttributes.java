package com.ciembro.healthApp.domain.drug.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DrugJsonAttributes {

    @JsonProperty("col1")
    private DrugJsonAttributeIntColumn uniqueDrugId;

    @JsonProperty("col2")
    private DrugJsonAttributeStringColumn tradeName;

    @JsonProperty("col3")
    private DrugJsonAttributeStringColumn internationalName;

    @JsonProperty("col4")
    private DrugJsonAttributeStringColumn productType;

    @JsonProperty("col7")
    private DrugJsonAttributeStringColumn dosage;

    @JsonProperty("col13")
    private DrugJsonAttributeStringColumn brand;

    @JsonProperty("col15")
    private DrugJsonAttributeStringColumn activeSubstance;

    @JsonProperty("col16")
    private DrugJsonAttributeStringColumn leafletUrl;
}
