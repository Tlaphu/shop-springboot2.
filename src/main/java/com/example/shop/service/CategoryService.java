package com.example.shop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.shop.entity.Category;
import com.example.shop.repository.CategoryRepository;

@Service
public class CategoryService {
    private final CategoryRepository repo;
    public CategoryService(CategoryRepository repo){ this.repo = repo;}
    public List<Category> getAll(){ return repo.findAll(); }
    public Optional<Category> find(Long id){ return repo.findById(id); }
    public Category save(Category c){ return repo.save(c); }
    public void delete(Long id){ repo.deleteById(id); }
}
