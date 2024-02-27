package com.sparta.outsourcing.dto.Review;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ReviewRequestDto {
    @NotBlank
    private int rating;
    @NotBlank
    private String content;
}
