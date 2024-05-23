package com.sparta.outsourcing.controller;

import com.sparta.outsourcing.dto.Review.ReviewRequestDto;
import com.sparta.outsourcing.dto.Review.ReviewResponseDto;
import com.sparta.outsourcing.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/stores/{store_id}/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    public ResponseEntity<ReviewResponseDto> createReview(@PathVariable Long storeId,
                                                          @RequestBody ReviewRequestDto requestDto) {
        ReviewResponseDto createdReview = reviewService.createReview(storeId, requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdReview);
    }

    @GetMapping
    public ResponseEntity<List<ReviewResponseDto>> getReviews(@PathVariable Long storeId) {
        List<ReviewResponseDto> reviews = reviewService.getReviews(storeId);
        return ResponseEntity.ok(reviews);
    }

    @PutMapping("/{review_id}")
    public ResponseEntity<ReviewResponseDto> updateReview(@PathVariable Long storeId,
                                                          @PathVariable Long reviewId,
                                                          @RequestBody ReviewRequestDto requestDto) {
        ReviewResponseDto updatedReview = reviewService.updateReview(storeId, reviewId, requestDto);
        return ResponseEntity.ok(updatedReview);
    }

    @DeleteMapping("/{review_id}")
    public ResponseEntity<ReviewResponseDto> deleteReview(@PathVariable Long storeId,
                                                          @PathVariable Long reviewId) {
        reviewService.deleteReview(storeId, reviewId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{review_id}/like")
    public ResponseEntity<ReviewResponseDto> likeReview(@PathVariable Long storeId,
                                                        @PathVariable Long reviewId) {
        reviewService.likeReview(storeId, reviewId);
        return ResponseEntity.ok().build();
    }
}
