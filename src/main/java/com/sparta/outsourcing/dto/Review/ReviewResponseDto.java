package com.sparta.outsourcing.dto.Review;

import com.sparta.outsourcing.entity.Review;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class ReviewResponseDto {
    private final Long id;
    private final int rating;
    private final String content;
    private final Timestamp createdAt;
    private final Timestamp updatedAt;
    private final Long storeId;
    private final Long userId;

    public ReviewResponseDto(Review review) {
        this.id = review.getId();
        this.rating = review.getRating();
        this.content = review.getContent();
        this.createdAt = review.getCreatedAt();
        this.updatedAt = review.getUpdatedAt();
        this.storeId = review.getStore().getId();
        this.userId = review.getUser().getId();
    }
}
