package com.sparta.outsourcing.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "menu")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menu_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "store_id")
    private Store store;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Long price;

    @Column(name = "description")
    private String description;

    public Menu(Store store, String name, Long price, String description) {
        this.store = store;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public void update(String name, Long price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }
}
