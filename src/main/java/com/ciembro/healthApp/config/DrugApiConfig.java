package com.ciembro.healthApp.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class DrugApiConfig {

    @Value("${drug.api.endpoint}")
    private String drugApiEndpoint;
}
