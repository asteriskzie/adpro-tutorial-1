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
        // happy path tries to create new payment 
        Payment payment = payments.get(0); 
        Payment result = paymentRepository.save(payment);

        assertEquals(payment, result); 
    }

    @Test 
    void testSaveUpdate() {
        // happy path tries to update existing payment 
        Payment originalPayment = payments.get(0);
        paymentRepository.save(originalPayment); 

        Payment newPayment = new Payment(originalPayment.getPaymentId(), "Voucher", PaymentStatus.REJECTED.getValue(), new HashMap<String, String>() {{
            put("voucherCode", "1234ABC5679");
        }});
        Payment savedPayment = paymentRepository.save(newPayment);

        assertEquals(savedPayment.getPaymentStatus(), PaymentStatus.REJECTED.getValue());
        assertEquals(paymentRepository.getAll().size(), 1); 
    }

    @Test 
    void testFindByIdIfFound() {
        // happy path tries to find payment by id that exist
        Payment payment = payments.get(0); 
        paymentRepository.save(payment);

        Payment findResult = paymentRepository.findById(payment.getPaymentId()); 
        assertEquals(findResult, payment); 
    }

    @Test
    void testFindByIdIfIdNotFound() {
        // unhappy path tries to find payment by id that doesn't exist
        Payment payment = payments.get(0);
        paymentRepository.save(payment);

        String otherPaymentId = payments.get(1).getPaymentId(); 
        Payment findResult = paymentRepository.findById(otherPaymentId);
        assertNull(findResult);
    }

    @Test
    void testGetAll() {
        // happy path test to get all payments
        for (Payment payment : payments) {
            paymentRepository.save(payment); 
        }

        List<Payment> result = paymentRepository.getAll();
        assertEquals(payments, result);
    }

}
