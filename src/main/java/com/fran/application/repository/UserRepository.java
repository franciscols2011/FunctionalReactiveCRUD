package com.fran.application.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.fran.application.entities.User;

public interface UserRepository extends ReactiveMongoRepository<User, String> {

}
