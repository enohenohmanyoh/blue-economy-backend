package com.blue_economy.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record ChatRequest(
        @NotBlank String text,
        @NotBlank @Pattern(regexp = "user|bot", message = "sender must be 'user' or 'bot'") String sender
) {}
