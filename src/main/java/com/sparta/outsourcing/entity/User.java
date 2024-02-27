package com.sparta.outsourcing.entity;

import com.sparta.outsourcing.service.UserRole;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    @Column(name = "introduce")
    private String introduce;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        this.role = UserRole.USER.toString();
        this.introduce = "일반 사용자 입니다.";
    }
}
