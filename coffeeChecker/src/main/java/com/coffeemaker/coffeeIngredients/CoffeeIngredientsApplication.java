package com.coffeemaker.coffeeIngredients;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class CoffeeIngredientsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoffeeIngredientsApplication.class, args);
	}
}
