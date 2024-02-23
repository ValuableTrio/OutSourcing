package org.example.outsourcing.store.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.example.outsourcing.owner.entity.Owner;

@Entity
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


}
