package com.blue_economy.repository;

import com.blue_economy.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    // Custom method to find a pending payment by email
    Optional<Payment> findByEmailAndStatus(String email, String status);

}
