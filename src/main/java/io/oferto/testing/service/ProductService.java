package io.oferto.testing.service;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import io.oferto.testing.model.Product;
import io.oferto.testing.repository.ProductRepository;

@Service
public class ProductService {
	private final ProductRepository productRepository;
	
	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	@Cacheable(value = "products")
	public List<Product> findAll() {
        return productRepository.findAll();
    }
	
    public Product findById(String productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("No product found for productId: " + productId));
    }
    
    public Product findByName(String name) {
        return productRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("No product found for name: " + name));
    }
}
