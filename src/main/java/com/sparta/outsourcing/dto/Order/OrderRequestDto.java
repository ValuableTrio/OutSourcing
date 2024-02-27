package com.sparta.outsourcing.dto.Order;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class OrderRequestDto {
    @NotBlank
    private Long storeId;
    @NotBlank
    private Long userId;
    @NotBlank
    private Long totalPrice;
    @NotBlank
    private String status;
    @NotBlank
    private String requests;
}
