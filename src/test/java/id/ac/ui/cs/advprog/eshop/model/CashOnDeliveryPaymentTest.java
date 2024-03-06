package id.ac.ui.cs.advprog.eshop.model;

import enums.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CashOnDeliveryPaymentTest {
    private String paymentId;
    private Map<String, String> paymentData;

    @BeforeEach
    public void setUp() {
        this.paymentId = "13652556-5e4f-4b6d-8f7f-3f6e3e3f6e3e";
        this.paymentData = new HashMap<>() {{
            put("address", "Jalan-jalan ke sana ke mari");
            put("deliveryFee", "5000");
        }};
    }

    @Test
    public void testCreateCashOnDeliveryPaymentSuccess() {
        CashOnDeliveryPayment payment = new CashOnDeliveryPayment(paymentId, paymentData);
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getPaymentStatus());
    }

    @Test
    public void testCreateCashOnDeliveryPaymentEmptyData() {
        paymentData.put("address", "");
        VoucherPayment payment = new VoucherPayment(paymentId, paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getPaymentStatus());
    }

    @Test
    public void testCreateVoucherPaymentNoData() {
        paymentData.remove("deliveryFee");
        VoucherPayment payment = new VoucherPayment(paymentId, paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getPaymentStatus());
    }
}
