package com.order_service.service;

import com.order_service.constant.OrderStatus;
import com.order_service.dto.OrderCreateRequestDto;
import com.order_service.dto.OrderDto;
import com.order_service.entity.Orders;
import com.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepo;

    //define a stream bridge
    private final StreamBridge streamBridge;


    public Orders createOrder(OrderCreateRequestDto dto){
        Orders order = new Orders();
        order.setOrderId(UUID.randomUUID().toString());
        order.setAmount(dto.getAmount());
        order.setStatus(OrderStatus.CREATED);
        order.setUserId(dto.getUserId());
        order.setProductId(dto.getProductId());
        order.setOrderDate(LocalDate.now());
        //Orders save successfully
        orderRepo.save(order);

        //Create event and publish for payment service
        OrderDto orderDto = new OrderDto(order.getOrderId(),Integer.parseInt(order.getAmount()),
                order.getStatus().toString());
        Message<OrderDto> msg = MessageBuilder.withPayload(orderDto).build();
        streamBridge.send("orderCreated",msg);

        return order;
    }
}
