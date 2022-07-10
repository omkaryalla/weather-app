package com.wether.app.authservice.service;

import java.util.Optional;

import com.wether.app.authservice.domain.User;

import java.util.List;

public interface UserService {
    
    public User saveUser(User user);

    public User updateUser(User user);

    public void deleteUser(User user);

    public List<User> getAllUsers();

    public Optional<User> getUserByUserName(String userName);

    public Optional<User> getUserByUserNameAndPassword(String userName, String password);
}
