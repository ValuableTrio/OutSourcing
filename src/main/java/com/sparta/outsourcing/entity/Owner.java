package com.sparta.outsourcing.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "owner")
public class Owner extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "business_number")
    private String businessNumber;

    @Column(name = "state")
    @Enumerated(value = EnumType.STRING)
    private OwnerAccountState state;

    public Owner(String email, String password) {
        this.email = email;
        this.password = password;
        this.state = OwnerAccountState.INPROGRESS;
    }
}
