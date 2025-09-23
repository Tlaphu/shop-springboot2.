package com.example.shop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.shop.entity.Order;
import com.example.shop.entity.OrderDetail;
import com.example.shop.entity.ShoppingCart;
import com.example.shop.entity.User;
import com.example.shop.repository.OrderDetailRepository;
import com.example.shop.repository.OrderRepository;
import com.example.shop.repository.ShoppingCartRepository;
import com.example.shop.repository.UserRepository;

@Service
public class OrderService {
    private final OrderRepository orderRepo;
    private final OrderDetailRepository detailRepo;
    private final ShoppingCartRepository cartRepo;
    private final UserRepository userRepo;

    public OrderService(OrderRepository orderRepo, OrderDetailRepository detailRepo,
                        ShoppingCartRepository cartRepo, UserRepository userRepo) {
        this.orderRepo = orderRepo;
        this.detailRepo = detailRepo;
        this.cartRepo = cartRepo;
        this.userRepo = userRepo;
    }

    @Transactional
    public Order checkout(Long userId) {
        User u = userRepo.findById(userId).orElseThrow();
        List<ShoppingCart> items = cartRepo.findByUserId(userId);
        if (items.isEmpty()) throw new RuntimeException("Cart empty");

        Order order = new Order();
        order.setUser(u);
        order.setStatus("CREATED");
        order = orderRepo.save(order);

        List<OrderDetail> details = new ArrayList<>();
        for (ShoppingCart ci : items) {
            OrderDetail d = new OrderDetail();
            d.setOrder(order);
            d.setProduct(ci.getProduct());
            d.setQuantity(ci.getQuantity());
            d.setPrice(ci.getProduct().getPrice());
            details.add(detailRepo.save(d));
            cartRepo.delete(ci); 
        }
        order.setDetails(details);

        return orderRepo.save(order);
    }

    public List<Order> getByUser(Long userId) {
        return orderRepo.findByUserId(userId);
    }

    public List<Order> getAll() {
        return orderRepo.findAll();
    }

    public Order changeStatus(Long orderId, String status) {
        Order o = orderRepo.findById(orderId).orElseThrow();
        o.setStatus(status);
        return orderRepo.save(o);
    }

    @Transactional
    public void deleteOrder(Long orderId) {
        Order order = orderRepo.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        orderRepo.delete(order);
    }
}
