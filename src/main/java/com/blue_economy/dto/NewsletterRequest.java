package com.blue_economy.dto;

import lombok.Data;

@Data

public class NewsletterRequest {
    private String name;
    private String email;
    private String recaptchaToken;  // ✅ comes from frontend

    // getters and setters
}
