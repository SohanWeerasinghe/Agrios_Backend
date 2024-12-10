package com.example.Agrios_Product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.example.Agrios_Product.model")
public class AgriosProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgriosProductApplication.class, args);
	}

}
