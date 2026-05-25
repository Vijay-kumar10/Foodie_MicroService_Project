package com.order_service.dto;

public record OrderDto(
        String orderId,
        int amount,
        String status
) {
}
