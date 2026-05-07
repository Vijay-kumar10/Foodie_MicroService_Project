package com.restaurand_service.service;
import com.restaurand_service.dto.RestaurantDto;

import java.util.List;

public interface RestaurantService {

    RestaurantDto saveRestaurant(RestaurantDto dto);

    RestaurantDto updateRestaurant(RestaurantDto dto, String id);

    RestaurantDto getRestaurantById(String id);

    List<RestaurantDto> getAllRestaurants();

    List<RestaurantDto> findByName(String name);

    void deleteRestaurant(String id);
}