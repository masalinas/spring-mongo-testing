package io.oferto.testing.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;
import org.hamcrest.Matchers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.oferto.testing.model.Product;
import io.oferto.testing.service.ProductService;

@WebMvcTest(controllers = ProductController.class)
class ProductControllerTests {
    @Autowired
    MockMvc mockMvc;

	@MockBean
    ProductService productService;
	
	@Autowired
	private ObjectMapper objectMapper;

    @Test
    @DisplayName("Test get all products")
    void testGetAllProducts() throws Exception {
		@SuppressWarnings("serial")
		List<Product> products = new ArrayList<Product>() {{		  
			add(Product.builder().id("a").name("Apple").price(0L).build());			
		}};
 
        Mockito.when(productService.findAll()).thenReturn(products);
 
        mockMvc.perform(get("/products").contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].name", Matchers.is("Apple")));
    }
    
    @Test
    @DisplayName("Test get by product id")
    void testGetProductById() throws Exception {
		Product product = Product.builder().id("a").name("Apple").price(0L).build();    	
  
		Mockito.when(productService.findById("a")).thenReturn(product);
		
        mockMvc.perform(get("/products/{productId}", "a").contentType("application/json")
                .content(objectMapper.writeValueAsString(product)))
				.andExpect(status().isOk());
    }
    
    @Test
    @DisplayName("Test get by product name")
    void testGetProductByName() throws Exception {
		Product product = Product.builder().id("a").name("Apple").price(0L).build();    	
  
		Mockito.when(productService.findByName("Apple")).thenReturn(product);
		
        mockMvc.perform(get("/products/name/{name}", "Apple").contentType("application/json")
                .content(objectMapper.writeValueAsString(product)))
				.andExpect(status().isOk());
    }
}
