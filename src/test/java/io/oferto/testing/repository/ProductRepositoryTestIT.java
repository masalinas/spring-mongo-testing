package io.oferto.testing.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import io.oferto.testing.model.Product;

@DataMongoTest(excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)
class ProductRepositoryTestIT {
	@Autowired 
	private ProductRepository productRepository;
	
	@Test
	@Tag("Repository tests")
	@DisplayName("this test verifies get all products")	
	void testFindProductsByName() {
		assertThat(productRepository).isNotNull();
		
		// when
		productRepository.save(Product.builder().name("Apple").price(10.0F).build());
		productRepository.save(Product.builder().name("Orange").price(5.0F).build());
		
		// then
		assertEquals(2, productRepository.findAll().size());
	}
}
