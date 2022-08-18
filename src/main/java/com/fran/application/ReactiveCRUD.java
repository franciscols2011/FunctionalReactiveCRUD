package com.fran.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class ReactiveCRUD {

	public static void main(String[] args) {
		SpringApplication.run(ReactiveCRUD.class, args);
	}

}
