package org.example.outsourcing.order.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.example.outsourcing.store.entity.Store;
import org.example.outsourcing.user.entity.User;

@Entity
@NoArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    private Long totalPrice;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
}
