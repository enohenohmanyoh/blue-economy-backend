package com.blue_economy.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "event_registrations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EventRegisterMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column(name = "age_group", nullable = false)
    private String ageGroup;

    @Column(nullable = false)
    private String nationality;

    @Column(nullable = false)
    private String gender;

    // Optional: track registration timestamp
    @Column(name = "registered_at", updatable = false)
    private java.time.LocalDateTime registeredAt;

    @PrePersist
    protected void onCreate() {
        this.registeredAt = java.time.LocalDateTime.now();
    }
}
