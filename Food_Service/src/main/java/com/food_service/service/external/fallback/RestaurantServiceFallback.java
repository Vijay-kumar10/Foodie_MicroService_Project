package com.food_service.service.external.fallback;

import com.food_service.dto.RestaurantDto;
import com.food_service.service.external.RestaurantService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RestaurantServiceFallback implements RestaurantService {
    @Override
    public RestaurantDto getById(String restaurantId) {
        System.out.println("Restaurant Service Fallback executed.");
        return null;
    }

    @Override
    public List<RestaurantDto> getAllRestaurants() {
        return List.of();
    }

    @Override
    public RestaurantDto createRestaurant(RestaurantDto restaurantDto) {
        return null;
    }

    @Override
    public void deleteRestaurant(String restaurantId) {

    }
}
