package id.ac.ui.cs.advprog.eshop.model;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

import enums.PaymentStatus;

@Builder
@Getter
public class Payment {
    String paymentId; 
    String paymentMethod; 
    String paymentStatus; 
    Map<String,String> paymentData; 

    public Payment(String paymentId, String paymentMethod, String paymentStatus, Map<String, String> paymentData) {
        this.paymentId = paymentId;
        this.paymentMethod = paymentMethod;        
        this.paymentData = paymentData;
        this.setPaymentStatus(paymentStatus);
    }

    public void setPaymentStatus(String paymentStatus) {
        if (PaymentStatus.contains(paymentStatus)) {
            this.paymentStatus = paymentStatus; 
        } else {
            throw new IllegalArgumentException();
        }
    }
}
