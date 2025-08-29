package com.blue_economy.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // --- Personal Information ---
    private String fullName;
    private String gender;
    private String ageGroup;
    private String nationality;
    private String contactNumber;
    private String email;

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

    private String createdAt; // or LocalDateTime if you prefer
}
