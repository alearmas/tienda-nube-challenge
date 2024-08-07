package com.aarmas.tiendaNube;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.aarmas.tiendaNube.repositories")
public class TiendaNubeApplication {

	public static void main(String[] args) {
		SpringApplication.run(TiendaNubeApplication.class, args);
	}

}
