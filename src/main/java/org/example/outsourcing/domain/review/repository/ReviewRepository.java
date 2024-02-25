package org.example.outsourcing.domain.review.repository;

import org.example.outsourcing.domain.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
