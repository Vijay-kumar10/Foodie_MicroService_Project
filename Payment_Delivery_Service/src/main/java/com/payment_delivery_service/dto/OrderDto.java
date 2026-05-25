package com.payment_delivery_service.dto;

public record OrderDto(
        String orderId,
        int amount,
        String status
) {
}