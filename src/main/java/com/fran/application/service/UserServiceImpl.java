package com.fran.application.service;

import org.springframework.stereotype.Service;

import com.fran.application.entities.User;
import com.fran.application.repository.UserRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {

		this.userRepository = userRepository;
	}

	@Override
	public Mono<User> save(User user) {

		return userRepository.save(user);
	}

	@Override
	public Mono<User> delete(String id) {

		return userRepository.findById(id).flatMap(p -> this.userRepository.deleteById(p.getId()).thenReturn(p));
	}

	@Override
	public Mono<User> update(String id, User user) {

		return userRepository.findById(id).flatMap(user1 -> {

			user.setId(id);
			return save(user);
		}).switchIfEmpty(Mono.empty());
	}

	@Override
	public Flux<User> findAll() {

		return userRepository.findAll();
	}

	@Override
	public Mono<User> findById(String id) {

		return userRepository.findById(id);
	}

}
