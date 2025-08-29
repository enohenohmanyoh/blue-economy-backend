package com.blue_economy.controller;

import com.blue_economy.dto.NewsletterRequest;
import com.blue_economy.service.RecaptchaService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/newsletter")
public class NewsletterController {

    @Autowired
    private RecaptchaService recaptchaService;

    @PostMapping("/subscribe")
    public String subscribe(@RequestBody NewsletterRequest request) {
        boolean captchaValid = recaptchaService.verify(request.getRecaptchaToken());

        if (!captchaValid) {
            return "reCAPTCHA validation failed!";
        }

        // ✅ Save to DB or send email
        return "Subscription successful!";
    }
}
