package org.example.outsourcing.domain.user.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String email;

    @Column(nullable = false)
    private String password;

    private String introduce;

    public User(String email, String password, String introduce) {
        this.email = email;
        this.password = password;
        this.introduce = introduce;
    }

    public boolean isNotPasswordMatch(PasswordEncoder passwordEncoder, String password){
        return !passwordEncoder.matches(password, this.password);
    }
}
