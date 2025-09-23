package com.example.shop.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.shop.entity.Product;
import com.example.shop.entity.ShoppingCart;
import com.example.shop.entity.User;
import com.example.shop.repository.ProductRepository;
import com.example.shop.repository.ShoppingCartRepository;
import com.example.shop.repository.UserRepository;

@Service
public class CartService {
    private final ShoppingCartRepository cartRepo;
    private final ProductRepository productRepo;
    private final UserRepository userRepo;

    public CartService(ShoppingCartRepository cartRepo, ProductRepository productRepo, UserRepository userRepo){
        this.cartRepo = cartRepo; this.productRepo = productRepo; this.userRepo = userRepo;
    }

    public List<ShoppingCart> getByUser(Long userId){ return cartRepo.findByUserId(userId); }

    public ShoppingCart addToCart(Long userId, Long productId, int quantity){
        User u = userRepo.findById(userId).orElseThrow();
        Product p = productRepo.findById(productId).orElseThrow();
        
        return cartRepo.findByUserId(userId).stream()
                .filter(ci -> ci.getProduct().getId().equals(productId))
                .findFirst()
                .map(ci -> { ci.setQuantity(ci.getQuantity()+quantity); return cartRepo.save(ci); })
                .orElseGet(() -> {
                    ShoppingCart c = new ShoppingCart();
                    c.setUser(u); c.setProduct(p); c.setQuantity(quantity);
                    return cartRepo.save(c);
                });
    }

    public void removeItem(Long cartId){ cartRepo.deleteById(cartId); }
    public void clearUserCart(Long userId){
        cartRepo.findByUserId(userId).forEach(ci -> cartRepo.delete(ci));
    }
}
