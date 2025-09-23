package com.example.shop.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.shop.entity.OrderDetail;
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {}
