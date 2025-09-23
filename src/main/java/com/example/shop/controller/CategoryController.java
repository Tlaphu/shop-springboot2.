package com.example.shop.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shop.entity.Category;
import com.example.shop.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService srv;
    public CategoryController(CategoryService srv){ this.srv = srv; }

    @GetMapping public List<Category> all(){ return srv.getAll(); }
    @PostMapping public Category create(@RequestBody Category c){ return srv.save(c); }
    @PutMapping("/{id}") public Category update(@PathVariable Long id, @RequestBody Category body){
        Category c = srv.find(id).orElseThrow();
        c.setName(body.getName()); c.setActive(body.getActive());
        return srv.save(c);
    }
    @PatchMapping("/{id}/toggle") public Category toggle(@PathVariable Long id){
        Category c = srv.find(id).orElseThrow();
        c.setActive(!c.getActive());
        return srv.save(c);
    }
    @DeleteMapping("/{id}") public void delete(@PathVariable Long id){ srv.delete(id); }
}
