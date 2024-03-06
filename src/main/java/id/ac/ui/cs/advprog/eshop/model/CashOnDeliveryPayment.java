package id.ac.ui.cs.advprog.eshop.model;

import enums.PaymentStatus;

import java.util.Map;

public class CashOnDeliveryPayment extends Payment {
    private boolean notNullOrEmpty(String data) {
        return data != null && !data.isEmpty();
    }

    private boolean isValidData(Map<String,String> paymentData) {
        String address = paymentData.get("address");
        String deliveryFee = paymentData.get("deliveryFee");

        return notNullOrEmpty(address) && notNullOrEmpty(deliveryFee);
    }
    public CashOnDeliveryPayment(String paymentId, Map<String, String> paymentData) {
        super(paymentId, "CashOnDelivery", PaymentStatus.REJECTED.getValue(), paymentData);

        if (isValidData(paymentData)) {
            this.setPaymentStatus(PaymentStatus.SUCCESS.getValue());
        }
    }
}
