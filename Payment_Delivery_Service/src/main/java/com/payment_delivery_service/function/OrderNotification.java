package com.payment_delivery_service.function;

import com.payment_delivery_service.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import java.util.function.Consumer;

@Configuration
@RequiredArgsConstructor
public class OrderNotification {

    private final StreamBridge streamBridge;

    @Bean
    public Consumer<OrderDto> orderEvent() {
        return orderDto -> {
            System.out.println("Order received from supplier.");
            System.out.println("Order id : "+orderDto.orderId());
            System.out.println("Order status : "+orderDto.status());
            System.out.println("Order amount : "+orderDto.amount());
            System.out.println("-------Fine-------");
            System.out.println();

            //now we publish an event of acknowledgement
            acknowledgeOrder(orderDto);
        };
    }


    public void acknowledgeOrder(OrderDto orderDto) {
        //Create acknowledge event for Order service
        Message<OrderDto> msg = MessageBuilder.withPayload(orderDto).build();
        streamBridge.send("orderReceived-out-0", msg);
        System.out.println("Order Received Acknowledge Send to supplier...");
        System.out.println("------Acknowledge Send successfully------");
        System.out.println();

    }
}
