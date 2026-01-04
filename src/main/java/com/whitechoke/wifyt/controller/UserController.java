package com.whitechoke.wifyt.controller;

import com.whitechoke.wifyt.dto.User;
import com.whitechoke.wifyt.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/user")
@AllArgsConstructor
@CrossOrigin
public class UserController {

    private final UserService service;

    @PostMapping("register")
    public ResponseEntity<User> createUser(@RequestBody User user) {

        var createdUser = service.createUser(user);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdUser);
    }

    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody User user) {

        var token = service.verify(user);

        return  ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(token);

    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {

        var user = service.getUserById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(user);

    }

    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id,
                                           @RequestBody User userToUpdate) {

        var updatedUser = service.updateUser(id, userToUpdate);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(updatedUser);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<User> deleteUserByID(@PathVariable Long id) {

        service.deleteUserById(id);

        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }
}
