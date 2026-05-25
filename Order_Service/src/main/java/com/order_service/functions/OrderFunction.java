package com.order_service.functions;

import com.order_service.dto.OrderDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Configuration
public class OrderFunction {

//    @Bean
//    public Supplier<String> test(){
//        return ()->"This is testing";
//    }
//
//    @Bean
//    public Function<String,String> test2(){
//        return (str-> str.toUpperCase());
//    }
//
//
//    @Bean
//    public Supplier<Map<String,String>> welcome(){
//        return ()->Map.of("message","Welcome to Orders Service");
//    }
    @Bean
    public Function<OrderDto,String> createOrder(){

        return orderDto -> {
            System.out.println("Orders Created");
            System.out.println(orderDto.orderId());
            System.out.println(orderDto.amount());
            System.out.println(orderDto.status());
            return "Orders create with id : "+ orderDto.orderId();
        };
    }

    @Bean
    public Consumer<OrderDto> orderReceived() {
        return orderDto -> {
            System.out.println("Order Acknowledge received from consumer.");
            System.out.println("Order id : "+orderDto.orderId());
            System.out.println("Order status : "+orderDto.status());
            System.out.println("Order amount : "+orderDto.amount());
            System.out.println("-------Fine-------");
            System.out.println();

        };
    }
}
