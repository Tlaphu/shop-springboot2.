package com.example.shop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.shop.entity.User;
import com.example.shop.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository repo;
    public UserService(UserRepository repo) { this.repo = repo; }

    public List<User> getAll() { return repo.findAll(); }
    public Optional<User> findById(Long id) { return repo.findById(id); }
    public User save(User u) { return repo.save(u); }
    public void delete(Long id) { repo.deleteById(id); }
    public Optional<User> findByUsername(String username) { return repo.findByUsername(username); }
}
