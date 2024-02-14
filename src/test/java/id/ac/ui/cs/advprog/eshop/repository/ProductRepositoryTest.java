package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;
    @BeforeEach
    void setUp() {

    }
    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("ab558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }
    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }
    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("ab558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("dc99b6f4-69c2-4a6e-afc3-112e29e5b6cb");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testDeleteProductExist() {
        Product product = new Product();
        product.setProductId("ab558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        productRepository.delete("ab558e9f-1c39-460e-8860-71af6af63bd6");
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testDeleteProductNotExist() {
        Product product = new Product();
        product.setProductId("ab558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        productRepository.delete("ab558e9f-1c39-460e-8860-71af6af63bd7");
        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
    }

    @Test 
    void testEditProduct() {
        Product product = new Product();
        product.setProductId("ab558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product editedProduct = new Product();
        product.setProductId("ab558e9f-1c39-460e-8860-71af6af63bd6");
        editedProduct.setProductName("Sampo Cap BengBeng");
        editedProduct.setProductQuantity(50);
        productRepository.edit("ab558e9f-1c39-460e-8860-71af6af63bd6", editedProduct);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(editedProduct.getProductId(), savedProduct.getProductId());
        assertEquals(editedProduct.getProductName(), savedProduct.getProductName());
        assertEquals(editedProduct.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test 
    void testEditProductNotExist() {
        Product product = new Product();        
        product.setProductId("real-id");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Product editedProduct = new Product();
        editedProduct.setProductId("fake-id");
        editedProduct.setProductName("Sampo Cap BengBeng");
        editedProduct.setProductQuantity(50);
        productRepository.edit("fake-id", editedProduct);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());

        Product savedProduct = productIterator.next();
        assertNotEquals(editedProduct.getProductId(), savedProduct.getProductId());
        assertNotEquals(editedProduct.getProductName(), savedProduct.getProductName());
        assertNotEquals(editedProduct.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testGetById() {
        Product product = new Product("Willow", 10); 
        String id = product.getProductId();
        productRepository.create(product);

        Product foundProduct = productRepository.getById(id);
        assertEquals(product, foundProduct);
    }

    @Test
    void testGetByIdNotExist() {
        Product product = new Product("Willow", 10);         
        productRepository.create(product);

        Product foundProduct = productRepository.getById("mampusgaada"); 
        assertNull(foundProduct);
    }
}
