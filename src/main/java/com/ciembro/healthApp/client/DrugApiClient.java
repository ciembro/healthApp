package com.ciembro.healthApp.client;

import com.ciembro.healthApp.config.DrugApiConfig;
import com.ciembro.healthApp.domain.drug.api.DrugApiDto;
import com.ciembro.healthApp.domain.drug.api.DrugJsonDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DrugApiClient {

    private final DrugApiConfig drugApiConfig;
    private final RestTemplate restTemplate;

    public List<DrugJsonDto> loadDrugListFromApi(){
        try {
            List<DrugJsonDto> drugs = new ArrayList<>();
            DrugApiDto response = restTemplate.getForObject(drugApiConfig.getDrugApiEndpoint(), DrugApiDto.class);

            if (response != null){
                String last = response.getPaginationLinks().getLast();
                while (response != null && !response.getPaginationLinks().getSelf().equals(last)){

                    drugs.addAll(response.getDrugs().stream()
                            .filter(d -> d.getDrugJsonAttributes().getProductType().getValue().equals("Ludzki"))
                            .collect(Collectors.toList()));
                    String next = response.getPaginationLinks().getNext();
                    response = restTemplate.getForObject(next, DrugApiDto.class);

                }
            }
            return drugs;

        } catch (RestClientException e){
            System.out.println(e.getMessage());
            return new ArrayList<>();
        }
    }

}
