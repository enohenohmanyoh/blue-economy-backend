package com.blue_economy.controller;

import com.blue_economy.service.OpenAIService;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/openai")
public class OpenAIController {

    private final OpenAIService openAIService;

    public OpenAIController(OpenAIService openAIService) {
        this.openAIService = openAIService;
    }

    @PostMapping("/chat")
    public ResponseEntity<Map<String, String>> chat(@RequestBody ChatRequest request) {
        String response = openAIService.getResponse(request.getPrompt());
        return ResponseEntity.ok(Map.of("response", response));
    }

    @Data
    public static class ChatRequest {
        private String prompt;
    }
}
