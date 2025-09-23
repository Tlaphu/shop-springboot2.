package com.example.shop.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double price;
    private Boolean active;
    private Integer stock;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonBackReference   
    private Category category;

    // ==== Constructors ====
    public Product() {}

    public Product(String name, Double price, Boolean active, Integer stock, Category category) {
        this.name = name;
        this.price = price;
        this.active = active;
        this.stock = stock;
        this.category = category;
    }

    // ==== Getters & Setters ====
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
