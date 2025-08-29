package com.blue_economy.service;

import com.theokanning.openai.OpenAiService;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.completion.CompletionResult;
import org.springframework.stereotype.Service;

@Service
public class OpenAIService {

    private final OpenAiService openAiService;

    public OpenAIService() {
        // Replace with your real OpenAI API key
        this.openAiService = new OpenAiService("YOUR_OPENAI_API_KEY");
    }

    public String getResponse(String prompt) {
        CompletionRequest request = CompletionRequest.builder()
                .prompt(prompt)
                .model("text-davinci-003") // or "gpt-3.5-turbo"
                .maxTokens(200)
                .temperature(0.7)
                .build();

        CompletionResult result = openAiService.createCompletion(request);
        return result.getChoices().get(0).getText().trim();
    }
}
