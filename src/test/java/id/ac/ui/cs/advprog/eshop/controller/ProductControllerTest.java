package id.ac.ui.cs.advprog.eshop.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;


@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {
    
    private MockMvc mockMvc;

    @Mock
    private ProductService service;

    @InjectMocks
    private ProductController controller;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void testCreateProductPage() throws Exception {
        mockMvc.perform(get("/product/create"))
               .andExpect(status().isOk())
               .andExpect(view().name("createProduct"));
    }

    @Test 
    public void testCreateProductPost() throws Exception {
        Product product = new Product("Cimory Matcha", 2);                

        mockMvc.perform(post("/product/create")
                .flashAttr("product", product))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("list"));
        
        verify(service, times(1)).create(product);
    }

    @Test 
    public void testProductListPage() throws Exception {
        when(service.findAll()).thenReturn(Arrays.asList(
            new Product("Minuman", 2), 
            new Product("Makanan", 3)
        )); 

        mockMvc.perform(get("/product/list"))
            .andExpect(status().isOk())
            .andExpect(view().name("productList")); 
    }

    @Test 
    public void testEditProductPage() throws Exception {
        Product product = new Product("Kopiko", 19); 
        String id = product.getProductId(); 
        when(service.getById(id)).thenReturn(product); 

        mockMvc.perform(get("/product/edit/" + id))
            .andExpect(status().isOk())
            .andExpect(view().name("editProduct"));
    }

    @Test 
    public void testEditProductPost() throws Exception {
        Product product = new Product("Test Product", 4);
        String id = product.getProductId(); 
        mockMvc.perform(post("/product/edit/" + id)
            .flashAttr("product", product))
            .andExpect(status().is3xxRedirection())
            .andExpect(redirectedUrl("../list"));
        
            verify(service, times(1)).edit(id, product);
    }

    @Test 
    public void testDeleteProduct() throws Exception {
        Product product = new Product("Test Product", 4);
        String id = product.getProductId(); 
        mockMvc.perform(delete("/product/delete/" + id))
            .andExpect(status().isOk());

        verify(service, times(1)).delete(id);
    }
}
