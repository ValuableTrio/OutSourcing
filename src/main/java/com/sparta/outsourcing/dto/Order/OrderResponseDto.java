package com.sparta.outsourcing.dto.Order;


import com.sparta.outsourcing.entity.Order;
import lombok.Getter;

@Getter
public class OrderResponseDto {
    private final Long id;
    private final Long storeId;
    private final Long userId;
    private final Long totalPrice;
    private final String status;
    private final String requests;

    public OrderResponseDto(Order order) {
        this.id = order.getId();
        this.storeId = order.getStore().getId();
        this.userId = order.getUser().getId();
        this.totalPrice = order.getTotalPrice();
        this.status = order.getStatus();
        this.requests = order.getRequests();
    }
}
