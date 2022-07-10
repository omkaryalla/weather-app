package com.wether.app.authservice.service;

import java.util.Map;

import com.wether.app.authservice.domain.User;

public interface SecurityService {
    Map<String, String> getAuthToken(User user);

    boolean validateToken(String token);
}
