package com.ciembro.healthApp.domain.drug.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DrugJsonDto {

    @JsonProperty("id")
    private String id;

    @JsonProperty("attributes")
    private DrugAttributesDto drugJsonAttributes;


}
