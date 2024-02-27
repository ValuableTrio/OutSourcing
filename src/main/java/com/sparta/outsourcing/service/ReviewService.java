package com.sparta.outsourcing.service;

import com.sparta.outsourcing.dto.Review.ReviewRequestDto;
import com.sparta.outsourcing.dto.Review.ReviewResponseDto;
import com.sparta.outsourcing.entity.Review;
import com.sparta.outsourcing.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewResponseDto createReview(Long storeId, ReviewRequestDto requestDto) {
        Review review = new Review();
        review.setRating(requestDto.getRating());
        review.setContent(requestDto.getContent());

        Timestamp timestamp = Timestamp.from(Instant.now());
        review.setCreatedAt(timestamp);

        Review savedReview = reviewRepository.save(review);
        return new ReviewResponseDto(savedReview);
    }

    public ReviewResponseDto updateReview(Long storeId, Long reviewId, ReviewRequestDto requestDto) {
        Optional<Review> optionalReview = reviewRepository.findById(reviewId);
        if (optionalReview.isPresent()) {
            Review review = optionalReview.get();
            review.setRating(requestDto.getRating());
            review.setContent(requestDto.getContent());
            review.setUpdatedAt(new Timestamp(System.currentTimeMillis())); // Update updatedAt timestamp
            Review updatedReview = reviewRepository.save(review);
            return new ReviewResponseDto(updatedReview);
        } else {
            throw new RuntimeException("해당 ID를 가진 리뷰를 찾을 수 없습니다: " + reviewId);
        }
    }

    public void deleteReview(Long storeId, Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }

    public List<ReviewResponseDto> getReviews(Long storeId) {
        List<Review> reviews = reviewRepository.findByStoreId(storeId);
        return reviews.stream()
                .sorted(Comparator.comparing(Review::getUpdatedAt).reversed())
                .map(ReviewResponseDto::new)
                .collect(Collectors.toList());
    }

    public void likeReview(Long storeId, Long reviewId) {
        Optional<Review> optionalReview = reviewRepository.findById(reviewId);
        if (optionalReview.isPresent()) {
            Review review = optionalReview.get();
            reviewRepository.save(review);
        } else {
            throw new RuntimeException("해당 ID를 가진 리뷰를 찾을 수 없습니다: " + reviewId);
        }
    }
}
