package io.oferto.testing.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document("Product")
public class Product {

	 @Id 
	 private String id;
	 
	 @NotBlank
	 @Size(min = 1, max = 20)
	 private String name;
	 @NotBlank
	 @Positive
	 private float price;
}
