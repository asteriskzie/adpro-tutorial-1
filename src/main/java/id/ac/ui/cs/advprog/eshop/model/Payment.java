package id.ac.ui.cs.advprog.eshop.model;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

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
        this.paymentStatus = paymentStatus;
        this.paymentData = paymentData;
    }
}
