package com.wether.app.authservice.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wether.app.authservice.domain.User;
import com.wether.app.authservice.service.SecurityService;
import com.wether.app.authservice.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class AuthController {
    
    private UserService userService;
    private SecurityService securityService;

    public AuthController(UserService userService, SecurityService securityService) {
        this.userService = userService;
        this.securityService = securityService;
    }

    @PostMapping(value = "/login")
    public ResponseEntity<Map<String, String>> loginUser(@RequestBody User user) {
        Map<String, String> response = new HashMap<>();
        Optional<User> userByUserName = userService.getUserByUserName(user.getUserName());
        if(!userByUserName.isPresent()) {
            response.put("message", "User not found");
            response.put("status", HttpStatus.NOT_FOUND.toString());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
        User user1 = userByUserName.get();
        if(!user1.getPassword().equals(user.getPassword())) {
            response.put("message", "Invalid password");
            response.put("status", HttpStatus.UNAUTHORIZED.toString());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        Map<String, String> tokenMap = securityService.getAuthToken(user1);
        return ResponseEntity.status(HttpStatus.OK).body(tokenMap);
    }

    @PostMapping(value = "/authenticate")
    public ResponseEntity<Map<String, String>> authenticateUser(@RequestHeader("Authorization") String token) {
        Map<String, String> response = new HashMap<>();
        if(!securityService.validateToken(token.substring(7))) {
            response.put("message", "Invalid token");
            response.put("status", HttpStatus.UNAUTHORIZED.toString());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
        response.put("message", "Valid token");
        response.put("status", HttpStatus.OK.toString());
        return ResponseEntity.status(HttpStatus.OK).body(response);
        
    }
}
