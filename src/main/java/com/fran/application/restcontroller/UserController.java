package com.fran.application.restcontroller;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fran.application.entities.User;
import com.fran.application.service.UserService;

@RestController
// me
@RequestMapping(path = "/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {

        this.userService = userService;
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    private Mono<User> save(@RequestBody User user) {

        return userService.save(user);
    }

    @DeleteMapping("/delete/{id}")
    private Mono<ResponseEntity<String>> delete(@PathVariable("id") String id) {

        return userService.delete(id)
                .flatMap(user -> Mono.just(ResponseEntity.ok("Deleted Successfully! :)")))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));

    }

    @PutMapping("/update/{id}")
    private Mono<ResponseEntity<User>> update(@PathVariable("id") String id, @RequestBody User user) {

        return userService.update(id, user)
                .flatMap(user1 -> Mono.just(ResponseEntity.ok(user1)))
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));

    }

    @GetMapping(value = "/findall")
    private Flux<User> findAll() {

        return userService.findAll();
    }

}
