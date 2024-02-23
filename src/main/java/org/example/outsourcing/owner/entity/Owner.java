package org.example.outsourcing.owner.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.example.outsourcing.user.entity.UserRole;

@Entity
@NoArgsConstructor
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 50)
    private String businessNumber;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;
}
