package com.order_service.entity;

import com.order_service.constant.OrderStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

@Data
@ToString
@Entity
public class Orders {
    @Id
    private String orderId;
    private String amount;
    private String userId;
    private LocalDate orderDate;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
    private String productId;

}
