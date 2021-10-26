package io.oferto.testing.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import io.oferto.testing.exception.ProductNotFoundException;
import io.oferto.testing.model.Product;
import io.oferto.testing.repository.ProductRepository;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
	@InjectMocks
	ProductService productService;
	
	@Mock
	private ProductRepository productRepository;
	 
	@Test
	@Tag("Service tests")
	@DisplayName("Should Return All Products")
    void findAllTest() {
		@SuppressWarnings("serial")
		List<Product> products = new ArrayList<Product>() {{		  
			add(Product.builder().id("a").name("Apple").price(0L).build());		 
			add(Product.builder().id("b").name("Orange").price(0L).build());
		}};
		
		/*
		List<Product> products = Lists.newArrayList(
				Product.builder().id("a").name("Apple").price(0L).build(),
				Product.builder().id("b").name("Orange").price(0L).build()
	    );*/
		
		when(productRepository.findAll())
			.thenReturn(products);
		
		assertEquals(2, productService.findAll().size());
	}
	
	@Test
	@Tag("Service tests")
	@DisplayName("Should Return a Product When Product Id Exist")
    void findByIdOKTest() {		
		when(productRepository.findById("a"))
			.thenReturn(Optional.of(Product.builder().id("a").name("Apple").price(0L).build()));
		
		Product product = productService.findById("a");
		
		verify(productRepository).findById("a");
		
		assertEquals("Apple", product.getName());
		assertEquals(0L, product.getPrice());
	}
	
	@Test
	@Tag("Service tests")
	@DisplayName("Should Return a Product When Product name Exist")
    void findByNameOKTest() {		
		when(productRepository.findByName("Apple"))
			.thenReturn(Optional.of(Product.builder().id("a").name("Apple").price(0L).build()));
		
		Product product = productService.findByName("Apple");
				
		verify(productRepository).findByName("Apple");
		
		assertEquals("Apple", product.getName());
		assertEquals(0L, product.getPrice());
	}
	
	@Test
	@Tag("Service tests")
	@DisplayName("Should Throw Product Not Found Exception When Id Does Not Exists")
    void findByIdKOTest() {
		final String productId = "a";
		
		ProductNotFoundException productNotFoundException = assertThrows(ProductNotFoundException.class,
                () -> productService.findById(productId));
		
		assertEquals("No product found for its productId: " + productId, productNotFoundException.getMessage());		
	}
	
	@Test
	@Tag("Service tests")
	@DisplayName("Should Throw Product Not Found Exception When Name Does Not Exists")
    void findByNameKOTest() {
		when(productRepository.findByName(any(String.class)))
		  .thenThrow(new ProductNotFoundException("No product found for its name: Melon"));
				
		assertThrows(ProductNotFoundException.class, () -> productService.findByName("Melon"));	
	}
}
