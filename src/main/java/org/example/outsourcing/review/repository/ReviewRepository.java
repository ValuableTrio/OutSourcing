package org.example.outsourcing.review.repository;

import org.example.outsourcing.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
