package com.order_service.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class OrderCreateRequestDto{

    String orderId;
    String productId;
    String userId;
    String amount;
    String status;
}
