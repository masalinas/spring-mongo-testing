package io.oferto.testing;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.assertj.core.api.Assertions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import io.oferto.testing.repository.ProductRepository;
import io.oferto.testing.controller.ProductController;
import io.oferto.testing.service.ProductService;

@SpringBootTest
class TestingApplicationTestIT {	
	@Autowired
    ProductRepository productRepository;
	
	@Autowired
    ProductService productService;
	
	@Autowired
    ProductController productController;
		
	@Test
	@Tag("Main tests")
	@DisplayName("This test verifies context is loaded")	
	void contextLoads() {
		Assertions.assertThat(productRepository).isNotNull();
		Assertions.assertThat(productService).isNotNull();
		Assertions.assertThat(productController).isNotNull();
	}
}
