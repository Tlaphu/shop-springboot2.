package com.example.shop.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.shop.entity.ShoppingCart;
import com.example.shop.service.CartService;

@RestController
@RequestMapping("/api/cart")
public class CartController {
    private final CartService srv;
    public CartController(CartService srv){ this.srv = srv; }

    @GetMapping("/user/{userId}")
    public List<ShoppingCart> getByUser(@PathVariable Long userId){ return srv.getByUser(userId); }

    @PostMapping("/add")
    public ShoppingCart add(@RequestParam Long userId, @RequestParam Long productId, @RequestParam(defaultValue = "1") int qty){
        return srv.addToCart(userId, productId, qty);
    }

    @DeleteMapping("/{cartId}") public void remove(@PathVariable Long cartId){ srv.removeItem(cartId); }

    @DeleteMapping("/clear/{userId}") public void clear(@PathVariable Long userId){ srv.clearUserCart(userId); }
}
