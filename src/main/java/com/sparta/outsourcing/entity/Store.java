package com.sparta.outsourcing.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "store")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "description")
    private String description;

    @Column(name = "min_order_price")
    private Long minOrderPrice;

    @Column(name = "dibs_count")
    private Long dibsCount;

    @Column(name = "rating")
    private int rating;

    @Column(name = "review_count")
    private String reviewCount;

    @Column(name = "status")
    private int status;
}