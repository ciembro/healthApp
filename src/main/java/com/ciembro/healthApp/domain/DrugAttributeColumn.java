package com.ciembro.healthApp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DrugAttributeColumn {

//    @JsonProperty("repr")
//    private String repr;

    @JsonProperty("val")
    private String value;
}
