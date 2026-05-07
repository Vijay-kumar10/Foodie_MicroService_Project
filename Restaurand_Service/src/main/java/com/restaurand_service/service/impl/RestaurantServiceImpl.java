package com.restaurand_service.service.impl;

import com.restaurand_service.dto.RestaurantDto;
import com.restaurand_service.entity.Restaurant;
import com.restaurand_service.repository.RestaurantRepository;
import com.restaurand_service.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepo;
    private final ModelMapper mapper;

    @Override
    public RestaurantDto saveRestaurant(RestaurantDto restaurantDto) {

        //generated random id for restaurant
        restaurantDto.setId(UUID.randomUUID().toString());

        Restaurant restaurant = mapper.map(restaurantDto, Restaurant.class);

        Restaurant saved = restaurantRepo.save(restaurant);

        return mapper.map(saved, RestaurantDto.class);
    }

    @Override
    public RestaurantDto updateRestaurant(RestaurantDto dto, String id) {

        Restaurant restaurant = restaurantRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        restaurant.setName(dto.getName());
        restaurant.setAddress(dto.getAddress());
        restaurant.setPhone(dto.getPhone());
        restaurant.setPicture(dto.getPicture());
        restaurant.setPictures(dto.getPictures());
        restaurant.setOpen(dto.isOpen());
        restaurant.setOpenTime(dto.getOpenTime());
        restaurant.setCloseTime(dto.getCloseTime());
        restaurant.setAboutRestaurant(dto.getAboutRestaurant());

        Restaurant updated = restaurantRepo.save(restaurant);

        return mapper.map(updated, RestaurantDto.class);
    }

    @Override
    public RestaurantDto getRestaurantById(String id) {

        Restaurant restaurant = restaurantRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        return mapper.map(restaurant, RestaurantDto.class);
    }

    @Override
    public List<RestaurantDto> getAllRestaurants() {

        return restaurantRepo.findAll()
                .stream()
                .map(res -> mapper.map(res, RestaurantDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<RestaurantDto> findByName(String name) {

        return restaurantRepo.findByNameContainingIgnoreCase(name)
                .stream()
                .map(res -> mapper.map(res, RestaurantDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteRestaurant(String id) {

        Restaurant restaurant = restaurantRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        restaurantRepo.delete(restaurant);
    }
}
