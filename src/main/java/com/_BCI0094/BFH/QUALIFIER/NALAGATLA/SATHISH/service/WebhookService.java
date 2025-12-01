package com._BCI0094.BFH.QUALIFIER.NALAGATLA.SATHISH.service;

import  com._BCI0094.BFH.QUALIFIER.NALAGATLA.SATHISH.model.GenerateWebhookRequest;
import  com._BCI0094.BFH.QUALIFIER.NALAGATLA.SATHISH.model.GenerateWebhookResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WebhookService {

    @Value("${bfh.name}")
    private String name;

    @Value("${bfh.regNo}")
    private String regNo;

    @Value("${bfh.email}")
    private String email;

    @Value("${bfh.generateWebhookUrl}")
    private String generateWebhookUrl;

    private final RestTemplate restTemplate;

    public WebhookService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public GenerateWebhookResponse generateWebhook() {
        // build request body
        GenerateWebhookRequest body = new GenerateWebhookRequest();
        body.setName(name);
        body.setRegNo(regNo);
        body.setEmail(email);

        // set headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<GenerateWebhookRequest> requestEntity =
                new HttpEntity<>(body, headers);

        // call the API
        return restTemplate.postForObject(
                generateWebhookUrl,
                requestEntity,
                GenerateWebhookResponse.class
        );
    }
}
