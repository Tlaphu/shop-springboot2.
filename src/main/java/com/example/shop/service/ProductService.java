package com.example.shop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.shop.entity.Product;
import com.example.shop.repository.ProductRepository;

@Service
public class ProductService {
    private final ProductRepository repo;
    public ProductService(ProductRepository repo){ this.repo = repo; }
    public List<Product> getAll(){ return repo.findAll(); }
    public Optional<Product> find(Long id){ return repo.findById(id); }
    public Product save(Product p){ return repo.save(p); }
    public void delete(Long id){ repo.deleteById(id); }
}
