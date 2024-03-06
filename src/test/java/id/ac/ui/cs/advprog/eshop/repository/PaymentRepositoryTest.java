package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import enums.PaymentStatus;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.ArrayList;
import java.util.HashMap;

public class PaymentRepositoryTest {
    PaymentRepository paymentRepository; 
    List<Payment> payments; 

    @BeforeEach 
    void setUp() {
        paymentRepository = new PaymentRepository(); 
        
        payments = new ArrayList<>(); 
        Payment payment1 = new Payment("dummy-id-1", "Voucher", PaymentStatus.SUCCESS.getValue(), new HashMap<String,String>() {{
            put("voucherCode", "ESHOP1234ABC5678");
        }});
        payments.add(payment1);

        Payment payment2 = new Payment("dummy-id-2", "CashOnDelivery", PaymentStatus.SUCCESS.getValue(), new HashMap<String, String> () {{
            put("address", "Jl. ABC No. 1"); 
            put("deliveryFee", "8000"); 
        }}); 
        payments.add(payment2);
    }

    @Test
    void testSaveCreate() {
        Payment payment = payments.get(0); 
        Payment savedPayment = paymentRepository.create(payment);

        assertEquals(payment, savedPayment); 
    }

    @Test 
    void testSaveUpdate() {
        Payment originalSuccessPayment = payments.get(0);
        paymentRepository.create(originalSuccessPayment); 

        Payment newRejectedPayment = new Payment(
            originalSuccessPayment.getPaymentId(), 
            originalSuccessPayment.getPaymentMethod(), 
            PaymentStatus.REJECTED.getValue(),
            originalSuccessPayment.getPaymentData()
        );
        
        Payment savedPayment = paymentRepository.update(newRejectedPayment);

        assertEquals(savedPayment.getPaymentStatus(), PaymentStatus.REJECTED.getValue());
        assertEquals(paymentRepository.getAll().size(), 1); 
    }

    @Test 
    void testFindByIdIfFound() {
        Payment payment = payments.get(0); 
        paymentRepository.create(payment);

        Payment findResult = paymentRepository.findById(payment.getPaymentId()); 
        assertEquals(findResult, payment); 
    }

    @Test
    void testFindByIdIfIdNotFound() {
        Payment payment = payments.get(0);
        paymentRepository.create(payment);

        String otherPaymentId = payments.get(1).getPaymentId(); 
        Payment findResult = paymentRepository.findById(otherPaymentId);
        assertNull(findResult);
    }

    @Test
    void testGetAll() {
        for (Payment payment : payments) {
            paymentRepository.create(payment); 
        }

        List<Payment> result = paymentRepository.getAll();
        assertEquals(payments, result);
    }

}
