package id.ac.ui.cs.advprog.eshop.repository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Product;

public class OrderRepositoryTest {
    OrderRepository orderRepository; 
    List<Order> orders; 
    
    @BeforeEach
    void setUp() {
        orderRepository = new OrderRepository(); 

        List<Product> products = new ArrayList<>(); 
        Product product1 = new Product(); 
        product1.setProductId("13652556-5e4f-4b6d-8f7f-3f6e3e3f6e3e");
        product1.setProductName("Sampo Cap Bambang"); 
        product1.setProductQuantity(2); 
        products.add(product1); 

        orders = new ArrayList<>(); 
        Order order1 = new Order("13652556-012a-4b6d-8f7f-3f6e3e3f6e3e", 
            products, 1708560000L, "Safira Sudrajat");
        orders.add(order1); 
        Order order2 = new Order("25652556-012a-4b6d-8f7f-3f6e3e3f6e3e", 
            products, 1708560000L, "Safira Sudrajat");
        orders.add(order2); 
        Order order3 = new Order("e334ef40-012a-4b6d-8f7f-3f6e3e3f6e3e", 
            products, 1708560000L, "Bambang Sudrajat");
        orders.add(order3); 
    }
    
    @Test 
    void testSaveCreate() {
        Order order = orders.get(1); 
        Order result = orderRepository.save(order); 

        Order findResult = orderRepository.findById(orders.get(1).getId()); 
        assertEquals(order.getId(), result.getId()); 
        assertEquals(order.getId(), findResult.getId());
        assertEquals(order.getOrderTime(), findResult.getOrderTime()); 
        assertEquals(order.getAuthor(), findResult.getAuthor());
        assertEquals(order.getStatus(), findResult.getStatus()); 
    }

    @Test 
    void testSaveUpdate() {
        Order order = orders.get(1); 
        orderRepository.save(order); 
        Order newOrder = new Order(order.getId(), order.getProducts(), order.getOrderTime(),
            order.getAuthor(), OrderStatus.SUCCESS.getValue()); 
        Order result = orderRepository.save(newOrder); 
        
        Order findResult = orderRepository.findById(orders.get(1).getId()); 
        assertEquals(order.getId(), result.getId()); 
        assertEquals(order.getId(), findResult.getId()); 
        assertEquals(order.getOrderTime(), findResult.getOrderTime()); 
        assertEquals(order.getAuthor(), findResult.getAuthor()); 
        assertEquals(OrderStatus.SUCCESS.getValue(), findResult.getStatus()); 
    }

    @Test 
    void testFindByIdIfIdFound() {
        for (Order order : orders) {
            orderRepository.save(order); 
        }

        Order findResult = orderRepository.findById(orders.get(1).getId()); 
        assertEquals(orders.get(1).getId(), findResult.getId()); 
        assertEquals(orders.get(1).getOrderTime(), findResult.getOrderTime()); 
        assertEquals(orders.get(1).getAuthor(), findResult.getAuthor()); 
        assertEquals(orders.get(1).getStatus(), findResult.getStatus()); 
    }

    @Test 
    void testFindByIdIfIdNotFound() {
        for (Order order : orders) {
            orderRepository.save(order); 
        }

        Order findResult = orderRepository.findById("zczc"); 
        assertNull(findResult);
    }

    @Test
    void testFindAllByAuthorIfAuthorCorrect() {
        for (Order order : orders) {
            orderRepository.save(order); 
        }

        List<Order> orderList = orderRepository.findAllByAuthor(
            orders.get(1).getAuthor()); 
        assertEquals(2, orderList.size()); 
    }

    @Test 
    void testFindAllByAuthorIfAllLowercase() {
        orderRepository.save(orders.get(1)); 

        List<Order> orderList = orderRepository.findAllByAuthor(
            orders.get(1).getAuthor().toLowerCase()); 
        assertTrue(orderList.isEmpty()); 
    }
}
