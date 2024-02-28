package com.sparta.outsourcing.controller;

import com.sparta.outsourcing.dto.Order.OrderRequestDto;
import com.sparta.outsourcing.dto.Order.OrderResponseDto;
import com.sparta.outsourcing.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponseDto> placeOrder(@RequestBody OrderRequestDto requestDto) {
        OrderResponseDto responseDto = orderService.placeOrder(requestDto);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @PostMapping("/{orderId}/complete")
    public ResponseEntity<OrderResponseDto> completeOrder(@PathVariable Long orderId) {
        OrderResponseDto responseDto = orderService.completeOrder(orderId);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<OrderResponseDto> cancelOrder(@PathVariable Long orderId) {
        orderService.cancelOrder(orderId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponseDto> getOrder(@PathVariable Long orderId) {
        OrderResponseDto responseDto = orderService.getOrder(orderId);
        return ResponseEntity.status(HttpStatus.OK).body(responseDto);
    }

    @GetMapping("/owners/orders")
    public ResponseEntity<List<OrderResponseDto>> getOrdersForOwner(@RequestParam Long storeId) {
        List<OrderResponseDto> responseDtos = orderService.getOrdersForOwner(storeId);
        return ResponseEntity.status(HttpStatus.OK).body(responseDtos);
    }
}
