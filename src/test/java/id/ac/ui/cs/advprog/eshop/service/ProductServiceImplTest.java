package id.ac.ui.cs.advprog.eshop.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;

import java.util.ArrayList;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {
    
    @Mock
    private ProductRepository repository;
    
    @InjectMocks
    private ProductServiceImpl service;

    @BeforeEach
    public void setUp() {
        
    }

    @Test
    public void testCreateProduct() {
        Product product = new Product("Willow", 2);  
        when(repository.create(product)).thenReturn(product);

        Product createProduct = service.create(product);

        assertEquals(product, createProduct);
        verify(repository, times(1)).create(product); 
    }

    @Test
    public void testFindAllProducts() {
        ArrayList<Product> products = new ArrayList<Product>();
        when(repository.findAll()).thenReturn(products.iterator());

        ArrayList<Product> foundProducts = (ArrayList<Product>) service.findAll();

        assertEquals(products, foundProducts);
        verify(repository, times(1)).findAll();
    }

    @Test 
    public void testGetById() {
        Product product = new Product("Willow", 2); 
        String id = product.getProductId(); 

        when(repository.getById(id)).thenReturn(product);

        Product foundProduct = service.getById(id);
        assertEquals(product, foundProduct); 
        verify(repository, times(1)).getById(id);
    }

    @Test
    public void testEditProduct() {
        Product product = new Product("Willow", 2); 
        String id = product.getProductId(); 


        service.edit(id, product);
        verify(repository, times(1)).edit(id, product);
    }

    @Test
    public void testDeleteProduct() {
        Product product = new Product("Willow", 2); 
        String id = product.getProductId(); 

        service.delete(id);
        verify(repository, times(1)).delete(id);
    }
}
