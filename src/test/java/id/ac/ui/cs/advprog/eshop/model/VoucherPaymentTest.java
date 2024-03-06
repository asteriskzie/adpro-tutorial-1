package id.ac.ui.cs.advprog.eshop.model;

import enums.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VoucherPaymentTest {
    private String paymentId;
    private Map<String, String> paymentData;

    @BeforeEach
    public void setUp() {
        this.paymentId = "13652556-5e4f-4b6d-8f7f-3f6e3e3f6e3e";
        this.paymentData = new HashMap<>() {{
            put("voucherCode", "ESHOP1234ABC5678");
        }};
    }

    @Test
    public void testCreateVoucherPaymentSuccess() {
        VoucherPayment payment = new VoucherPayment(paymentId, paymentData);
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getPaymentStatus());
    }

    @Test
    public void testCreateVoucherPaymentNoData() {
        paymentData.clear();
        VoucherPayment payment = new VoucherPayment(paymentId, paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getPaymentStatus());
    }

    @Test
    public void testCreateVoucherPaymentWrongData() {
        paymentData.put("voucherCode", "awikwok");
        VoucherPayment payment = new VoucherPayment(paymentId, paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getPaymentStatus());
    }
}
