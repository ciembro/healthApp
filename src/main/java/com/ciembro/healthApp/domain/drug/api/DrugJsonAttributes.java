package com.ciembro.healthApp.domain.drug.api;

import com.ciembro.healthApp.domain.drug.api.DrugJsonAttributeColumn;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DrugJsonAttributes {

    @JsonProperty("col2")
    private DrugJsonAttributeColumn tradeName;

    @JsonProperty("col3")
    private DrugJsonAttributeColumn commonName;

    @JsonProperty("col4")
    private DrugJsonAttributeColumn productType;

    @JsonProperty("col7")
    private DrugJsonAttributeColumn dose;

    @JsonProperty("col13")
    private DrugJsonAttributeColumn brand;

    @JsonProperty("col15")
    private DrugJsonAttributeColumn activeSubstance;

    @JsonProperty("col16")
    private DrugJsonAttributeColumn leafletUrl;
}
