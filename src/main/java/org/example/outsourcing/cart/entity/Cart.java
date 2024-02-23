package org.example.outsourcing.cart.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.example.outsourcing.menu.entity.Menu;
import org.example.outsourcing.order.entity.Order;

@Entity
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id")
    private Menu menu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(nullable = false)
    private Long quantity;

    @Column(nullable = false)
    private Long price;
}
