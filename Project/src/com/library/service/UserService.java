package com.library.service;

import com.library.model.User;
import com.library.repository.LibraryRepository;

import java.util.List;

public class UserService {
    private LibraryRepository repository;

    public UserService(LibraryRepository repository) {
        this.repository = repository;
    }

    public void registerUser(String id, String name, String email) {
        User user = new User(id, name, email);
        repository.addUser(user);
    }

    public List<User> getAllUsers() {
        return repository.getAllUsers();
    }
}