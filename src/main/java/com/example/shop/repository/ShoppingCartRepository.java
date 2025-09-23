package com.example.shop.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.shop.entity.ShoppingCart;
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {
    List<ShoppingCart> findByUserId(Long userId);
}
