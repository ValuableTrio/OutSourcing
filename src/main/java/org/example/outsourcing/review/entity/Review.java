package org.example.outsourcing.review.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import org.example.outsourcing.global.entity.Timestamped;
import org.example.outsourcing.store.entity.Store;
import org.example.outsourcing.user.entity.User;

@NoArgsConstructor
@Entity
public class Review extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    @Column(nullable = false, columnDefinition = "TINYINT")
    private byte rating;

    @Column(nullable = false)
    private String content;
}
