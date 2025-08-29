package com.blue_economy.service;

import com.blue_economy.dto.PaymentRequest;
import com.blue_economy.dto.PaymentResponse;
import com.blue_economy.exception.CustomerException;
import com.blue_economy.model.Payment;
import com.blue_economy.repository.PaymentRepository;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final StripeService stripeService;

    public PaymentService(PaymentRepository paymentRepository, StripeService stripeService) {
        this.paymentRepository = paymentRepository;
        this.stripeService = stripeService;
    }

    @Transactional
    public PaymentResponse processPayment(PaymentRequest request) throws StripeException {
        Payment payment = new Payment();

        // --- Personal Information ---
        payment.setFullName(request.getFullName());
        payment.setEmail(request.getEmail());
        payment.setContactNumber(request.getContactNumber());
        payment.setGender(request.getGender());
        payment.setAgeGroup(request.getAgeGroup());
        payment.setNationality(request.getNationality());

        // --- Professional Background ---
        payment.setOccupation(request.getOccupation());
        payment.setOrganization(request.getOrganization());
        payment.setQualification(request.getQualification());
        payment.setSpecialization(request.getSpecialization());
        payment.setExperience(request.getExperience());
        payment.setPreviousTraining(request.getPreviousTraining());

        // --- Training Goals ---
        payment.setTrainingGoals(request.getTrainingGoals());
        payment.setOutcomes(request.getOutcomes());
        payment.setTrainingMode(request.getTrainingMode());
        payment.setRemarks(request.getRemarks());

        // --- Payment Info ---
        payment.setAmount(5000L);
        payment.setCurrency("usd");
        payment.setStatus("pending");
        payment.setCreatedAt(LocalDateTime.now().toString());

        payment = paymentRepository.save(payment);

        // --- Stripe PaymentIntent ---
        PaymentIntent intent = stripeService.createPaymentIntent(
                payment.getAmount(),
                payment.getCurrency(),
                payment.getFullName(),
                payment.getEmail()
        );

        payment.setStripePaymentIntentId(intent.getId());
        paymentRepository.save(payment);

        return buildPaymentResponse(payment, intent.getClientSecret());
    }

    @Transactional
    public PaymentResponse confirmPayment(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new CustomerException("Payment not found with id: " + id));

        try {
            PaymentIntent intent = stripeService.retrievePaymentIntent(payment.getStripePaymentIntentId());

            if ("succeeded".equals(intent.getStatus())) {
                payment.setStatus("confirmed");
                paymentRepository.save(payment);
            }

            return buildPaymentResponse(payment, intent.getClientSecret());
        } catch (Exception e) {
            throw new CustomerException("Stripe confirmation failed: " + e.getMessage());
        }
    }

    public List<Payment> listPayments() {
        return paymentRepository.findAll();
    }

    public void deletePayment(Long id) {
        if (!paymentRepository.existsById(id)) {
            throw new CustomerException("Payment not found with id: " + id);
        }
        paymentRepository.deleteById(id);
    }

    public Optional<Payment> findPaymentById(Long id) {
        return paymentRepository.findById(id);
    }

    private PaymentResponse buildPaymentResponse(Payment payment, String clientSecret) {
        return new PaymentResponse(
                clientSecret,
                payment.getId(),
                payment.getFullName(),
                payment.getEmail(),
                payment.getContactNumber(),
                payment.getGender(),
                payment.getAgeGroup(),
                payment.getNationality(),
                payment.getOccupation(),
                payment.getOrganization(),
                payment.getQualification(),
                payment.getSpecialization(),
                payment.getExperience(),
                payment.getPreviousTraining(),
                payment.getTrainingGoals(),
                payment.getOutcomes(),
                payment.getTrainingMode(),
                payment.getRemarks(),
                payment.getAmount(),
                payment.getCurrency(),
                payment.getStatus(),
                payment.getStripePaymentIntentId(),
                payment.getCreatedAt()
        );
    }
}
