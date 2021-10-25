package io.oferto.testing.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import io.oferto.testing.model.Product;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {	 
	public Optional<Product> findByName(String name);
}
