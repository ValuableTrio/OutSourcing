package com.sparta.outsourcing.service;

import com.sparta.outsourcing.dto.Order.OrderRequestDto;
import com.sparta.outsourcing.dto.Order.OrderResponseDto;
import com.sparta.outsourcing.entity.Order;
import com.sparta.outsourcing.entity.Store;
import com.sparta.outsourcing.entity.User;
import com.sparta.outsourcing.repository.OrderRepository;
import com.sparta.outsourcing.repository.StoreRepository;
import com.sparta.outsourcing.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final StoreRepository storeRepository;
    private final UserRepository userRepository;

    public OrderResponseDto placeOrder(OrderRequestDto requestDto) {
        Order order = new Order();

        Store store = storeRepository.findById(requestDto.getStoreId())
                .orElseThrow(() -> new RuntimeException("상점을 찾을 수 없습니다. 상점 ID: " + requestDto.getStoreId()));
        order.setStore(store);

        User user = userRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다. 사용자 ID: " + requestDto.getUserId()));
        order.setUser(user);

        order.setTotalPrice(requestDto.getTotalPrice());
        order.setStatus(requestDto.getStatus());
        order.setRequests(requestDto.getRequests());

        order = orderRepository.save(order);

        return new OrderResponseDto(order);
    }

    public OrderResponseDto completeOrder(Long orderId) {
        try {
            orderRepository.findById(orderId);
        } catch (NumberFormatException e) {
            throw new RuntimeException("주문 ID가 잘못되었습니다.");
        }

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("주문을 찾을 수 없습니다. 주문 ID: " + orderId));

        return new OrderResponseDto(order);
    }

    public void cancelOrder(Long orderId) {
        try {
            orderRepository.deleteById(orderId);
        } catch (NumberFormatException e) {
            throw new RuntimeException("주문 ID가 잘못되었습니다.");
        }

        orderRepository.deleteById(orderId);
    }

    public OrderResponseDto getOrder(Long orderId) {
        try {
            orderRepository.findById(orderId);
        } catch (NumberFormatException e) {
            throw new RuntimeException("주문 ID가 잘못되었습니다.");
        }

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("주문을 찾을 수 없습니다. 주문 ID: " + orderId));

        return new OrderResponseDto(order);
    }

    public List<OrderResponseDto> getOrdersForOwner(Long storeId) {
        List<Order> orders = orderRepository.findByStoreId(storeId);
        return orders.stream()
                .sorted(Comparator.comparing(Order::getId).reversed())
                .map(OrderResponseDto::new)
                .toList();
    }
}
