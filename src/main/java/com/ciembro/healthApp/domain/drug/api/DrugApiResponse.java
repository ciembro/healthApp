package com.ciembro.healthApp.domain.drug.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class DrugApiResponse {

    @JsonProperty("links")
    Links links;

    @JsonProperty("data")
    List<DrugJsonDto> drugs;
}