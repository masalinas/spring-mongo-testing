package io.oferto.testing.exception;

@SuppressWarnings("serial")
public class ProductNotFoundException extends RuntimeException {
	public ProductNotFoundException(String message) {
		super(message);
    }
}
