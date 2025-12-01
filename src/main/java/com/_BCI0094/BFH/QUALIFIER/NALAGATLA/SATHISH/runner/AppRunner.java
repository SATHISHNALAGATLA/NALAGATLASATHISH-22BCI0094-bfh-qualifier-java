package com._BCI0094.BFH.QUALIFIER.NALAGATLA.SATHISH.runner;

import  com._BCI0094.BFH.QUALIFIER.NALAGATLA.SATHISH.model.GenerateWebhookResponse;
import  com._BCI0094.BFH.QUALIFIER.NALAGATLA.SATHISH.service.SolutionSubmitService;
import  com._BCI0094.BFH.QUALIFIER.NALAGATLA.SATHISH.service.WebhookService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements CommandLineRunner {

    private final WebhookService webhookService;
    private final SolutionSubmitService solutionSubmitService;

    @Value("${bfh.finalQuery}")
    private String finalQuery;

    public AppRunner(WebhookService webhookService,
                     SolutionSubmitService solutionSubmitService) {
        this.webhookService = webhookService;
        this.solutionSubmitService = solutionSubmitService;
    }

    @Override
    public void run(String... args) throws Exception {
        // 1. Call generateWebhook API
        GenerateWebhookResponse response = webhookService.generateWebhook();

        if (response == null) {
            System.out.println("Failed to get webhook response");
            return;
        }

        String webhookUrl = response.getWebhook();
        String accessToken = response.getAccessToken();

        System.out.println("Received webhook: " + webhookUrl);
        System.out.println("Received accessToken: " + accessToken);

        // 2. Send final SQL query to the webhook
        solutionSubmitService.submitSolution(webhookUrl, accessToken, finalQuery);

        System.out.println("Final query submitted successfully.");
    }
}

