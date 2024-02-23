package org.example.outsourcing.cart.repository;

import org.example.outsourcing.cart.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
