package com.example.shop.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shop.entity.Category;
import com.example.shop.entity.Product;
import com.example.shop.service.CategoryService;
import com.example.shop.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService srv;
    private final CategoryService catSrv;
    public ProductController(ProductService srv, CategoryService catSrv){ this.srv = srv; this.catSrv = catSrv; }

    @GetMapping public List<Product> all(){ return srv.getAll(); }
    @GetMapping("/{id}") public Product get(@PathVariable Long id){ return srv.find(id).orElse(null); }

    @PostMapping
    public Product create(@RequestBody Product p){
        if(p.getCategory() != null && p.getCategory().getId() != null){
            Category c = catSrv.find(p.getCategory().getId()).orElse(null);
            p.setCategory(c);
        }
        return srv.save(p);
    }

    @PutMapping("/{id}")
    public Product update(@PathVariable Long id, @RequestBody Product body){
        Product p = srv.find(id).orElseThrow();
        p.setName(body.getName());
        p.setPrice(body.getPrice());
        p.setStock(body.getStock());
        p.setActive(body.getActive());
        if(body.getCategory() != null && body.getCategory().getId() != null){
            p.setCategory(catSrv.find(body.getCategory().getId()).orElse(null));
        }
        return srv.save(p);
    }

    @DeleteMapping("/{id}") public void delete(@PathVariable Long id){ srv.delete(id); }
}
