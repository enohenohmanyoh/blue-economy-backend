package com.blue_economy.controller;

import com.blue_economy.dto.ContactForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contact")

public class ContactController {

    @Autowired
    private JavaMailSender mailSender;

    @PostMapping
    public String sendContactEmail(@RequestBody ContactForm contactForm) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo("admin@example.com"); // admin email
            message.setSubject("New Contact Message from " + contactForm.getName());
            message.setText(
                    "Name: " + contactForm.getName() + "\n" +
                            "Email: " + contactForm.getEmail() + "\n" +
                            "Message: " + contactForm.getMessage()
            );
            mailSender.send(message);
            return "Email sent successfully!";
        } catch (Exception e) {
            e.printStackTrace();
            return "Failed to send email.";
        }
    }
}
