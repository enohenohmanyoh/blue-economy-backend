package com.blue_economy.service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class StripeService {

    public StripeService(@Value("${stripe.secret.key}") String secretKey) {
        Stripe.apiKey = secretKey;
    }

    // ✅ Create a PaymentIntent with fixed $50
    public PaymentIntent createPaymentIntent(Long amount, String currency, String name, String email) throws StripeException {
        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount(amount) // allow dynamic amount from PaymentService
                .setCurrency(currency)
                .setAutomaticPaymentMethods(
                        PaymentIntentCreateParams.AutomaticPaymentMethods.builder().setEnabled(true).build()
                )
                .putMetadata("customerName", name)
                .putMetadata("customerEmail", email)
                .build();

        return PaymentIntent.create(params);
    }

    // ✅ Retrieve an existing PaymentIntent (used when confirming/checking status)
    public PaymentIntent retrievePaymentIntent(String paymentIntentId) throws StripeException {
        return PaymentIntent.retrieve(paymentIntentId);
    }

    // ✅ Optionally confirm a PaymentIntent directly from backend (if you want manual confirmation flow)
    public PaymentIntent confirmPaymentIntent(String paymentIntentId) throws StripeException {
        PaymentIntent intent = PaymentIntent.retrieve(paymentIntentId);
        return intent.confirm();
    }
}
