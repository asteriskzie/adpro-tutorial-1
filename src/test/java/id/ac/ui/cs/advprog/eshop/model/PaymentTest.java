package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import enums.PaymentStatus;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;

public class PaymentTest {
    Payment payment;
    @BeforeEach
    void setUp() {
        this.payment = new Payment("dummy", "Voucher", PaymentStatus.SUCCESS.getValue(), new HashMap<String, String>(){{
            put("voucherCode", "ESHOP1234ABC5678");
        }});
    }

    @Test
    void testGetPaymentId() {
        assertEquals("dummy", this.payment.getPaymentId());
    }

    @Test
    void testGetPaymentMethod() {
        assertEquals("Voucher", this.payment.getPaymentMethod());
    }

    @Test
    void testGetPaymentStatus() {
        assertEquals(PaymentStatus.SUCCESS.getValue(), this.payment.getPaymentStatus());
    }

    @Test
    void testGetPaymentData() {
        assertEquals(new HashMap<String, String>(){{
            put("voucherCode", "ESHOP1234ABC5678");
        }}, this.payment.getPaymentData());
    }
}
