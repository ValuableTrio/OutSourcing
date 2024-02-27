package com.sparta.outsourcing.repository;

import com.sparta.outsourcing.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByStoreId(Long storeId);
}