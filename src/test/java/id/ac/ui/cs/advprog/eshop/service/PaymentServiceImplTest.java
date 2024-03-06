package id.ac.ui.cs.advprog.eshop.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.*;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceImplTest {
    @InjectMocks
    PaymentServiceImpl paymentService; 
    
    @Mock
    PaymentRepository paymentRepository;
    List<Payment> payments; 

    @BeforeEach
    void setUp() {
        List<Product> products = new ArrayList<>();
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductId("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        products.add(product1);

        List<Order> orders = new ArrayList<>();
        Order order1 = new Order("13652556-012a-4c07-b546-54eb1296d79b",
                products, 1798560000L, "Safira Sudrajat");
        orders.add(order1);
        Order order2 = new Order("7f9e15bb-4b15-42f4-aebc-c3af385fb078",
                products, 1798570000L, "Safira Sudrajat");
        orders.add(order2);

        payments = new ArrayList<>(); 
        Payment payment1 = new Payment(
            "3f0d7c38-4933-4e9b-b994-2552b5f8732d",
            "Voucher", 
            PaymentStatus.SUCCESS.getValue(), 
            new HashMap <> () {{
                put("voucherCode", "ESHOP1234ABC5678"); 
            }}
        ); 
        payments.add(payment1); 

        Payment payment2 = new Payment(
            "4b3e5b2a-1b3c-4e3d-a067-8d9984664716",
            "CashOnDelivery", 
            PaymentStatus.REJECTED.getValue(),
            new HashMap<>(){{
                put("address", "Jl. ABC No. 1"); 
                put("deliveryFee", null);
            }}
        ); 
        payments.add(payment2); 

        paymentService.paymentOrder.put(payment1, order1);
        paymentService.paymentOrder.put(payment2, order2);
    }

    @Test
    void testCreatePayment() {
        Payment payment = payments.get(0);
        Order order = paymentService.paymentOrder.get(payment);

        ArgumentCaptor<Payment> paymentCaptor = ArgumentCaptor.forClass(Payment.class);
        doAnswer(invocation -> {
            Payment argPayment = paymentCaptor.getValue();
            argPayment.setPaymentId(payment.getPaymentId());
            argPayment.setPaymentStatus(payment.getPaymentStatus());
            return argPayment;
        }).when(paymentRepository).create(paymentCaptor.capture());

        Payment result = paymentService.addPayment(order, payment.getPaymentMethod(), payment.getPaymentData());

        verify(paymentRepository, times(1)).create(paymentCaptor.capture());
        assertEquals(payment.getPaymentMethod(), result.getPaymentMethod());
        assertEquals(payment.getPaymentStatus(), result.getPaymentStatus());
        assertEquals(payment.getPaymentData(), result.getPaymentData());
    }

    @Test 
    void testCreatePaymentIfAlreadyExists() {
        Payment payment = payments.get(0);
        Order order = paymentService.paymentOrder.get(payment);

        ArgumentCaptor<Payment> paymentCaptor = ArgumentCaptor.forClass(Payment.class);
        doAnswer(invocation -> {
            Payment argPayment = paymentCaptor.getValue();
            argPayment.setPaymentId(payment.getPaymentId());
            argPayment.setPaymentStatus(payment.getPaymentStatus());
            return argPayment;
        }).when(paymentRepository).create(paymentCaptor.capture());

        paymentService.addPayment(order, payment.getPaymentMethod(), payment.getPaymentData());

        Payment otherPayment = payments.get(1);
        otherPayment.setPaymentId(payment.getPaymentId());
        Order otherOrder = paymentService.paymentOrder.get(otherPayment);

        doReturn(null).when(paymentRepository).create(paymentCaptor.capture());

        assertNull(paymentService.addPayment(order, payment.getPaymentMethod(), payment.getPaymentData())); 
    }

    @Test
    void testUpdateStatus() {
        Payment payment = payments.get(0);
        doReturn(payment).when(paymentRepository).findById(payment.getPaymentId());
        Payment result = paymentService.setStatus(payment, PaymentStatus.REJECTED.getValue());
        assertEquals(PaymentStatus.REJECTED.getValue(), result.getPaymentStatus());
    }

    @Test 
    void testUpdateStatusInvalidStatus() {
        Payment payment = payments.get(0);
        doReturn(payment).when(paymentRepository).findById(payment.getPaymentId());
        assertThrows(IllegalArgumentException.class,
            () -> paymentService.setStatus(payment, "MEOW"));

        verify(paymentRepository, times(0)).update(any(Payment.class));
    }

    @Test 
    void testUpdateStatusInvalidPaymentId() {
        Payment payment = payments.get(0);

        Payment invalidIdPayment = new Payment(
            "zczc",
            payment.getPaymentMethod(),
            payment.getPaymentStatus(),
            payment.getPaymentData()
        );
        doReturn(null).when(paymentRepository).findById("zczc");

        assertThrows(NoSuchElementException.class,
            () -> paymentService.setStatus(invalidIdPayment, PaymentStatus.REJECTED.getValue()));

        verify(paymentRepository, times(0)).update(any(Payment.class));
    }

    @Test
    void testGetPaymentIfIdFound() {
        Payment payment = payments.get(0);
        String paymentId = payment.getPaymentId();
        doReturn(payment).when(paymentRepository).findById(paymentId);
        Payment result = paymentService.getPayment(paymentId);
        assertEquals(payment, result); 
    }

    @Test 
    void testGetPaymentIfIdNotFound() {
        String notFoundId = "zczc";
        doReturn(null).when(paymentRepository).findById(notFoundId);
        assertNull(paymentService.getPayment(notFoundId)); 
    }

    @Test
    void testGetAllPayments() {
        doReturn(payments).when(paymentRepository).getAll();
        List<Payment> result = paymentService.getAllPayment();
        assertEquals(2, result.size());
    }
}
