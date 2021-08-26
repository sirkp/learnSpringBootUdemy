package com.example.learnSpringBoot.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import com.example.learnSpringBoot.entities.UserEntity;
import com.example.learnSpringBoot.exceptions.UserServiceException;
import com.example.learnSpringBoot.utils.Utils;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {
    Map<String, UserEntity> users;

    Utils utils;

    public UserController() {}

    public UserController(Utils utils) {
        this.utils = utils;
    }


    @GetMapping(path = "/{userId}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE} )
    public ResponseEntity<?> getUser(@PathVariable String userId) {
        if (users.containsKey(userId)) {
            return new ResponseEntity<UserEntity>(users.get(userId), HttpStatus.OK);
        } else {
            return new ResponseEntity<UserEntity>(HttpStatus.NO_CONTENT);
        }
    }


    @GetMapping()
    public ResponseEntity<?> getUsers(@RequestParam(value = "page") int page, @RequestParam(value = "limit", defaultValue = "50") int limit) { //using required or defaultValue we can make query params optional
        if (true) throw new UserServiceException("User Service Exception");
        String s = null;
        int n = s.length();
        return new ResponseEntity<UserEntity>(HttpStatus.OK);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<?>  createUser(@Valid @RequestBody UserEntity user) {
        if (users == null) {
            users = new HashMap<>();
        }

        String id = utils.generateUserId();

        if(users.get(user.getUserId()) == null) {
            users.put(user.getUserId(), user);
            return new ResponseEntity<UserEntity>(user, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<UserEntity>(HttpStatus.CONFLICT);
        }

    }

    @PutMapping(path = "/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable String userId, @Valid @RequestBody UserEntity user) {
        if (users.containsKey(userId)) {
            users.put(userId, user);
            return new ResponseEntity<UserEntity>(users.get(userId), HttpStatus.OK);
        } else {
            return new ResponseEntity<UserEntity>(HttpStatus.BAD_REQUEST);
        }   
    }

    @DeleteMapping(path = "/{userId}")
    public ResponseEntity<?> deleteUser(@PathVariable String userId) {
        if (users.containsKey(userId)) {
            users.remove(userId);
            return new ResponseEntity<UserEntity>(HttpStatus.OK);// ResponseEntity<Void> <-ResponseEntity.noContent().build();
        } else {
            return new ResponseEntity<String>("user with given id doesn't exist",HttpStatus.BAD_REQUEST);
        } 
    }
}
