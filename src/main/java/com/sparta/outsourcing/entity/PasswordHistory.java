package com.sparta.outsourcing.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "password_history")
@NoArgsConstructor
@AllArgsConstructor
public class PasswordHistory extends AuditingFields {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(nullable = false)
    private String password;

    public PasswordHistory(User user, String password) {
        this.user = user;
        this.password = password;
    }
}
