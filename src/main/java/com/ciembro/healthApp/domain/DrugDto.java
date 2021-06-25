package com.ciembro.healthApp.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.web.JsonPath;

@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class DrugDto {

    @JsonProperty("id")
    private String id;

    @JsonProperty("attributes")
    private DrugAttributes drugAttributes;


}
