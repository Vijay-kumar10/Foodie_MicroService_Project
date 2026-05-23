package com.restaurand_service.controller;

import com.restaurand_service.dto.RestaurantDto;
import com.restaurand_service.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    // Create Restaurant
    @PostMapping
    public ResponseEntity<RestaurantDto> createRestaurant(@RequestBody RestaurantDto dto) {

        RestaurantDto restaurant = restaurantService.saveRestaurant(dto);

        return new ResponseEntity<>(restaurant, HttpStatus.CREATED);
    }

    // Update Restaurant
    @PutMapping("/{id}")
    public ResponseEntity<RestaurantDto> updateRestaurant(
            @RequestBody RestaurantDto dto,
            @PathVariable String id) {

        RestaurantDto restaurant = restaurantService.updateRestaurant(dto, id);
        return ResponseEntity.ok(restaurant);
    }

    int count = 0;
    // Get Restaurant By Id
    @GetMapping("/{id}")
    public ResponseEntity<RestaurantDto> getRestaurantById(@PathVariable String id) {
        RestaurantDto restaurant =restaurantService.getRestaurantById(id);
        count++;
        if(count <=3){
            System.out.println("Retring : "+count);
            throw new RuntimeException("Server down");
        }
        return ResponseEntity.ok(restaurant);
    }

    // Get All Restaurants
    @GetMapping
    public ResponseEntity<List<RestaurantDto>> getAllRestaurants() {
        List<RestaurantDto> restaurants = restaurantService.getAllRestaurants();
        return ResponseEntity.ok(restaurants);
    }

    // Search By Name
    @GetMapping("/name/{name}")
    public ResponseEntity<List<RestaurantDto>> findByName( @PathVariable String name) {
        List<RestaurantDto> restaurants = restaurantService.findByName(name);
        return ResponseEntity.ok(restaurants);
    }

    // Delete Restaurant
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRestaurant( @PathVariable String id) {

        restaurantService.deleteRestaurant(id);

        return ResponseEntity.ok("Restaurant Deleted Successfully");
    }
}