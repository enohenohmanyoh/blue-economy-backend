package com.blue_economy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentResponse {

    private String clientSecret; // for processPayment only
    private Long paymentId;

    // --- Personal Information ---
    private String fullName;
    private String email;
    private String contactNumber;
    private String gender;
    private String ageGroup;
    private String nationality;

    // --- Professional Background ---
    private String occupation;
    private String organization;
    private String qualification;
    private String specialization;
    private String experience;
    private String previousTraining;

    // --- Training Goals ---
    private String trainingGoals;
    private String outcomes;
    private String trainingMode;
    private String remarks;

    // --- Payment Info ---
    private Long amount;
    private String currency;
    private String status;
    private String stripePaymentIntentId;
    private String createdAt;

    // Stripe

}
