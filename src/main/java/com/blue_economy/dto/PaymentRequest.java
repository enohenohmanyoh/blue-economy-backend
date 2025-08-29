package com.blue_economy.dto;

import lombok.Data;
import java.util.Map;

@Data
public class PaymentRequest {

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

    // --- Competencies ---
    private Map<String, String> competencies;

    // --- Training Goals ---
    private String trainingGoals;
    private String outcomes;
    private String trainingMode;
    private String remarks;
}
