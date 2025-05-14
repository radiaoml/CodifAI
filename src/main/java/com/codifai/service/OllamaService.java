// #OllamaService.java
package com.codifai.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.Map;

@Service
public class OllamaService {

    private final WebClient webClient;

    public OllamaService() {
        this.webClient = WebClient.builder()
                .baseUrl("http://localhost:11434")
                .build();
    }

    public String analyze(String prompt) {
        Map<String, Object> requestBody = Map.of(
                "model", "codellama",
                "prompt", prompt,
                "stream", false
        );

        return webClient.post()
                .uri("/api/generate")
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(Map.class)
                .map(resp -> (String) resp.get("response"))
                .block(); // Synchrone pour simplifier
    }
}
