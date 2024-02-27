package com.sparta.outsourcing.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @Column(nullable = false, length = 40)
    private String name;

    @Column(nullable = false, length = 50)
    private String address;

    @Column(nullable = false, length = 15)
    private String phone;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Long minPrice;

    @Column(nullable = false)
    private boolean status;


    public Store(Owner owner, String name, String address, String phone, String description, Long minPrice) {
        this.owner = owner;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.description = description;
        this.minPrice = minPrice;
        this.status = false;
    }

    public boolean isNotOwnerMatch(Owner owner) {
        return !this.owner.equals(owner);
    }

    public void update(String name, String address, String phoneNumber, String description, Long minPrice) {
        this.name = name;
        this.address = address;
        this.phone = phoneNumber;
        this.description = description;
        this.minPrice = minPrice;
    }
}
