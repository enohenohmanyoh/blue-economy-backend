package com.blue_economy.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import java.util.Map;

@Service
public class RecaptchaService {

    @Value("${google.recaptcha.secret}")
    private String recaptchaSecret;

    @Value("${google.recaptcha.verify-url}")
    private String recaptchaVerifyUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public boolean verify(String token) {
        String url = String.format("%s?secret=%s&response=%s",
                recaptchaVerifyUrl, recaptchaSecret, token);
        ResponseEntity<Map> response = restTemplate.postForEntity(url, null, Map.class);
        Map body = response.getBody();
        if (body == null) return false;

        return (Boolean) body.get("success");
    }
}
