package com.food_service.service.external;

import com.food_service.config.AppConstant;
import com.food_service.dto.RestaurantDto;
import com.food_service.service.external.fallback.RestaurantServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = AppConstant.RESTAURANT_SERVICE_NAME, fallback = RestaurantServiceFallback.class)
public interface RestaurantService {

    //get restaurant by id
    @GetMapping("/api/v1/restaurants/{id}")
    RestaurantDto getById(@PathVariable("id") String restaurantId);

    //get all restaurant
    @GetMapping("api/v1/restaurants")
    List<RestaurantDto> getAllRestaurants();

    //update the restaurant
    @PostMapping("api/v1/restaurants")
    RestaurantDto createRestaurant(@RequestBody RestaurantDto restaurantDto);

    //delete by id
    @DeleteMapping("api/v1/restaurants/{id}")
    void deleteRestaurant(@PathVariable("id") String restaurantId);

}
