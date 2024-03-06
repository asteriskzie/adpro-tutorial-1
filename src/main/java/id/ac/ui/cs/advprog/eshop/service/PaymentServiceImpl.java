package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;

import enums.PaymentStatus;


public class PaymentServiceImpl implements PaymentService {
    @Autowired 
    PaymentRepository paymentRepository;

    Map<Payment, Order> paymentOrder = new HashMap<>(); 

    @Override 
    public Payment addPayment(Order order, String method, Map<String, String> paymentData) {
        // TODO: add the new payment to the payment repository, save payment-id, order-id to paymentOrder
        Payment payment = new Payment(
            java.util.UUID.randomUUID().toString(),
            method,
            PaymentStatus.SUCCESS.getValue(),
            paymentData
        );
        payment = paymentRepository.create(payment); 
        return payment; 
    }

    @Override
    public Payment setStatus(Payment payment, String status) {
        // change status of a payment
        Payment savedPayment = paymentRepository.findById(payment.getPaymentId());
        if (savedPayment != null) {
            Payment newPayment = new Payment(
                    savedPayment.getPaymentId(),
                    savedPayment.getPaymentMethod(),
                    status,
                    savedPayment.getPaymentData()
            );
            paymentRepository.update(newPayment);
            return newPayment;
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public Payment getPayment(String paymentId) {
        // get payment by paymentId
        return paymentRepository.findById(paymentId); 
    }

    @Override
    public List<Payment> getAllPayment() {
        return paymentRepository.getAll(); 
    }
}
