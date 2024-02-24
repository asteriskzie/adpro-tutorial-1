package id.ac.ui.cs.advprog.eshop.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CarTest {
    Car car; 
    @BeforeEach
    void setUp() {
        this.car = new Car(); 
        this.car.setCarId("1"); 
        this.car.setCarName("Suzuki Jimny");
        this.car.setCarColor("Black");
        this.car.setCarQuantity(10);
    }
    @Test
    void testGetCarId() {
        assertEquals("1", this.car.getCarId());
    }
    @Test
    void testGetCarName() {
        assertEquals("Suzuki Jimny", this.car.getCarName());
    }
    @Test
    void testGetCarColor() {
        assertEquals("Black", this.car.getCarColor());
    }
    @Test
    void testGetCarQuantity() {
        assertEquals(10, this.car.getCarQuantity());
    }
}
