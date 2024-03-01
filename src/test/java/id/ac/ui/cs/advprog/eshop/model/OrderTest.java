package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.model.Product;
import net.bytebuddy.asm.Advice.OffsetMapping.Factory.Illegal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import enums.OrderStatus;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;


public class OrderTest {
    private List<Product> products; 
    @BeforeEach
    void setUp () {
        this.products = new ArrayList<>(); 

        Product product1 = new Product(); 
        product1.setProductId("13652556-5e4f-4b6d-8f7f-3f6e3e3f6e3e");
        product1.setProductName("Sampo Cap Bambang"); 
        product1.setProductQuantity(2); 
        
        Product product2 = new Product(); 
        product2.setProductId("a2c52556-5e4f-4b6d-8f7f-3f6e3e3f6e3e");
        product2.setProductName("Sabun Cap Usep");
        product2.setProductQuantity(1); 

        this.products.add(product1); 
        this.products.add(product2); 
    }

    @Test 
    void testCreateOrderEmptyProduct() {
        this.products.clear(); 

        assertThrows(IllegalArgumentException.class, () -> {
            Order order = new Order("13652556-012a-4b6d-8f7f-3f6e3e3f6e3e", 
                this.products, 1708560000L, "Safira Sudrajat"); 
        }); 
    }

    @Test 
    void testCreateOrderDefaultStatus() {
        Order order = new Order("13652556-012a-4b6d-8f7f-3f6e3e3f6e3e", 
            this.products, 1708560000L, "Safira Sudrajat"); 
        
        assertEquals(this.products, order.getProducts()); 
        assertEquals(2, order.getProducts().size());
        assertEquals("Sampo Cap Bambang", order.getProducts().get(0).getProductName()); 
        assertEquals("Sabun Cap Usep", order.getProducts().get(1).getProductName());

        assertEquals("13652556-012a-4b6d-8f7f-3f6e3e3f6e3e", order.getId());
        assertEquals(1708560000L, order.getOrderTime());
        assertEquals("Safira Sudrajat", order.getAuthor());
        assertEquals(OrderStatus.WAITING_PAYMENT.getValue(), order.getStatus());
    }

    @Test
    void testCreateOrderSuccessStatus() {
        Order order = new Order("13652556-012a-4b6d-8f7f-3f6e3e3f6e3e", 
            this.products, 1708560000L, "Safira Sudrajat", OrderStatus.SUCCESS.getValue());
        assertEquals(OrderStatus.SUCCESS.getValue(), order.getStatus());
    }

    @Test 
    void testCreateOrderInvalidStatus() {
        assertThrows(IllegalArgumentException.class, () -> {
            Order order = new Order("13652556-012a-4b6d-8f7f-3f6e3e3f6e3e", 
                this.products, 1708560000L, "Safira Sudrajat", "MEOW");
        }); 
    }

    @Test
    void testSetStatusToCancelled() {
        Order order = new Order("13652556-012a-4b6d-8f7f-3f6e3e3f6e3e", 
            this.products, 1708560000L, "Safira Sudrajat");
        order.setStatus(OrderStatus.CANCELLED.getValue());
        assertEquals(OrderStatus.CANCELLED.getValue(), order.getStatus());
    }

    @Test 
    void testSetStatusToInvalidStatus() {
        Order order = new Order("13652556-012a-4b6d-8f7f-3f6e3e3f6e3e", 
            this.products, 1708560000L, "Safira Sudrajat");
        assertThrows(IllegalArgumentException.class, () -> order.setStatus("MEOW")); 
    }
}
