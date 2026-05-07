package com.food_service.dto;

import lombok.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RestaurantDto {
    private String id;
    private String name;
    private String address;
    private String phone;
    private String picture;

    private List<String> pictures = new ArrayList<>();    //priority 1
    private boolean open  = false;

    //priority 2
    private LocalTime openTime;
    private LocalTime closeTime;


    private String aboutRestaurant;
}

