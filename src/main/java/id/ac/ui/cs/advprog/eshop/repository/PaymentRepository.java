package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;

import java.util.List;
import java.util.ArrayList;

public class PaymentRepository {
    private List<Payment> paymentData = new ArrayList<>();    

    public Payment create(Payment payment) {
        if (payment.getPaymentId() == null || findById(payment.getPaymentId()) != null) {
            throw new IllegalArgumentException("Payment ID is null or already exists");
        }

        paymentData.add(payment);
        return payment; 
    }

    public Payment update(Payment payment) {
        if (findById(payment.getPaymentId()) == null) {
            return create(payment);
            
        }

        int i = 0; 
        for (Payment savedPayment : paymentData) {
            if (savedPayment.getPaymentId().equals(payment.getPaymentId())) {
                paymentData.set(i, payment);
                return payment; 
            }
            i += 1; 
        }

        return payment; 
    }
    
    public Payment findById(String paymentId) {
        for (Payment savedPayment : paymentData) {
            if (savedPayment.getPaymentId().equals(paymentId)) {
                return savedPayment; 
            }
        }
        return null; 
    }

    public List<Payment> getAll() {
        return new ArrayList<>(paymentData); 
    }
}
