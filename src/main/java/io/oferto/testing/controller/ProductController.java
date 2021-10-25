package io.oferto.testing.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.oferto.testing.model.Product;
import io.oferto.testing.service.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private final ProductService productService;
	
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
		
	@GetMapping(value = "")
	public List<Product> getAllProducts() {
		logger.info("Getting all products.");
		
		return productService.findAll();
	}
	
	@GetMapping(value = "/{productId}")
	public Product getProductById(@PathVariable String productId) {
		logger.info("Getting product with ID: {}.", productId);
		
		return productService.findById(productId);
	}
	
	@GetMapping(value = "/name/{name}")
	public Product getProductByName(@PathVariable String name) {
		logger.info("Getting product with name: {}.", name);
		
		return productService.findByName(name);
	}
}
