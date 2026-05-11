package com.food_service.dto;

import com.food_service.entity.FoodCategory;
import com.food_service.entity.FoodType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FoodItemDto {
    private String id;

    private String title;

    private String description;

    private int quantity;

    private FoodType foodType;

    private boolean isOutOfStock;

    private String categoryId;

    private FoodCategory foodCategory;

    private String restaurantId;

    private RestaurantDto restaurantDto;
}
