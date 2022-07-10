package com.wether.app.authservice.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wether.app.authservice.domain.User;
import com.wether.app.authservice.service.UserService;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private UserService userService;
    
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping({"", "/"})
    public ResponseEntity<User> postCreateUser(@RequestBody User user){
        if(userService.getUserByUserName(user.getUserName()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(user);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(user));
    }

    @GetMapping({"", "/"})
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> allUsers = userService.getAllUsers();
        if(allUsers.isEmpty()){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(allUsers);
    }

    @GetMapping({"/{userName}"})
    public ResponseEntity<User> getUserByUserName(@PathVariable String userName){
        Optional<User> user = userService.getUserByUserName(userName);
        if(!user.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(user.get());
    }

    @PostMapping({"/{userName}"})
    public ResponseEntity<User> putUpdateUser(@PathVariable String userName, @RequestBody User user){
        Optional<User> userOptional = userService.getUserByUserName(userName);
        if(!userOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        userOptional.get().setUserName(user.getUserName());
        userOptional.get().setPassword(user.getPassword());
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(userOptional.get()));
    }

    
}
