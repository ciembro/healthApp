package com.ciembro.healthApp.domain.drug;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DrugAttributes {

    @JsonProperty("col2")
    private DrugAttributeColumn tradeName;

    @JsonProperty("col3")
    private DrugAttributeColumn commonName;

    @JsonProperty("col4")
    private DrugAttributeColumn productType;

    @JsonProperty("col7")
    private DrugAttributeColumn dose;

    @JsonProperty("col13")
    private DrugAttributeColumn brand;

    @JsonProperty("col15")
    private DrugAttributeColumn activeSubstance;

    @JsonProperty("col16")
    private DrugAttributeColumn leafletUrl;
}
