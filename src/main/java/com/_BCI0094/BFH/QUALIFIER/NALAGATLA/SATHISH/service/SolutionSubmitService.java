package com._BCI0094.BFH.QUALIFIER.NALAGATLA.SATHISH.service;

import com._BCI0094.BFH.QUALIFIER.NALAGATLA.SATHISH.service.WebhookService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class SolutionSubmitService {

    private final RestTemplate restTemplate;

    public SolutionSubmitService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public void submitSolution(String webhookUrl, String accessToken, String finalQuery) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // As per assignment: Authorization: <accessToken>
        headers.set("Authorization", accessToken);

        Map<String, String> body = new HashMap<>();
        body.put("finalQuery", finalQuery);

        HttpEntity<Map<String, String>> entity = new HttpEntity<>(body, headers);

        // We don't care about response body here
        restTemplate.postForEntity(webhookUrl, entity, String.class);
    }
}