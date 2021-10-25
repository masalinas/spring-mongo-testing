package io.oferto.testing.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import io.oferto.testing.model.Product;

@DataMongoTest(excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)
class ProductRepositoryTests {
	@Autowired 
	private ProductRepository productRepository;
	
	@Test
	void testFindProductsByName() {
		assertThat(productRepository).isNotNull();
		
		// when
		productRepository.save(Product.builder().name("Apple").price(10.0F).build());
		productRepository.save(Product.builder().name("Orange").price(5.0F).build());
		
		// then
		assertEquals(2, productRepository.findAll().size());
	}
}
