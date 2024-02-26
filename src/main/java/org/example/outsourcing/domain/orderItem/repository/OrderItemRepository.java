package org.example.outsourcing.domain.orderItem.repository;


import org.example.outsourcing.domain.orderItem.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
