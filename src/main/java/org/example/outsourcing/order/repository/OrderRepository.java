package org.example.outsourcing.order.repository;

import org.example.outsourcing.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
