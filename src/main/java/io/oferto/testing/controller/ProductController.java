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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping(value = "/api/products")
public class ProductController {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	
	private final ProductService productService;
	
	public ProductController(ProductService productService) {
		this.productService = productService;
	}
		
	@Operation(summary = "Get all products")
	@GetMapping(value = "")
	public List<Product> getAllProducts() {
		logger.info("Getting all products.");
		
		return productService.findAll();
	}
	
	@Operation(summary = "Get a product by its id")
	@ApiResponses(value = { 
		@ApiResponse(responseCode = "200", description = "Found the product", 
			content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class)) }), 
        @ApiResponse(responseCode = "404", description = "Product not found", content = @Content) })
	@GetMapping(value = "/{productId}")
	public Product getProductById(@PathVariable String productId) {
		logger.info("Getting product with ID: {}.", productId);
		
		return productService.findById(productId);
	}
	
	@Operation(summary = "Get a product by its name")
	@ApiResponses(value = { 
			@ApiResponse(responseCode = "200", description = "Found the product", 
				content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Product.class)) }), 
	        @ApiResponse(responseCode = "404", description = "Product not found", content = @Content) })	
	@GetMapping(value = "/name/{name}")
	public Product getProductByName(@PathVariable String name) {
		logger.info("Getting product with name: {}.", name);
		
		return productService.findByName(name);
	}
}
