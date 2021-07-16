package com.ciembro.healthApp.domain.drug.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DrugAttributesDto {

    @JsonProperty("col1")
    private DrugAttributeIntColumnDto uniqueDrugId;

    @JsonProperty("col2")
    private DrugAttributeStringColumnDto tradeName;

    @JsonProperty("col3")
    private DrugAttributeStringColumnDto internationalName;

    @JsonProperty("col4")
    private DrugAttributeStringColumnDto productType;

    @JsonProperty("col7")
    private DrugAttributeStringColumnDto dosage;

    @JsonProperty("col13")
    private DrugAttributeStringColumnDto brand;

    @JsonProperty("col15")
    private DrugAttributeStringColumnDto activeSubstance;

    @JsonProperty("col16")
    private DrugAttributeStringColumnDto leafletUrl;
}
