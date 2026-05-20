package com.food_service.service.impl;

import com.food_service.dto.FoodItemDto;
import com.food_service.dto.RestaurantDto;
import com.food_service.entity.FoodCategory;
import com.food_service.entity.FoodItem;
import com.food_service.repository.FoodCategoryRepo;
import com.food_service.repository.FoodItemRepo;
import com.food_service.service.FoodItemService;
import com.food_service.service.external.RestWebClientService;
import com.food_service.service.external.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodItemServiceImpl implements FoodItemService {

    private final FoodItemRepo repository;
    private final FoodCategoryRepo categoryRepository;
    private final ModelMapper modelMapper;
    private final RestTemplate restTemplate;

    //feignClient
    private final RestaurantService restaurantService;

    //webclient
    private final RestWebClientService restWebClientService;

    @Override
    public FoodItemDto create(FoodItemDto dto) {

        FoodCategory category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        FoodItem item = modelMapper.map(dto, FoodItem.class);

        item.setFoodCategory(category);

        FoodItem saved = repository.save(item);

        FoodItemDto response = modelMapper.map(saved, FoodItemDto.class);

        response.setCategoryId(saved.getFoodCategory().getId());

        return response;
    }

    @Override
    public Page<FoodItemDto> getAll(int page, int size) {

        Pageable pageable = PageRequest.of(page, size);

        Page<FoodItem> foodPage = repository.findAll(pageable);

        return foodPage.map(item -> {

            FoodItemDto dto = modelMapper.map(item, FoodItemDto.class);

            dto.setCategoryId(item.getFoodCategory().getId());

            return dto;
        });
    }

    @Override
    public FoodItemDto getById(String id) {

        FoodItem item = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Food Item not found"));

        //call to restaurant to service to get Restaurant data
        //restaurant url
//        String resturantUrl = "http://localhost:9091/api/v1/restaurants/" + item.getRestaurantId();
//        RestaurantDto restaurantDto = restTemplate.getForObject(resturantUrl, RestaurantDto.class);

        //get restaurant using web-client
//       RestaurantDto restaurantDto = restWebClientService.getById(item.getRestaurantId());

        //get restaurant using feign client
        RestaurantDto restaurantDto = restaurantService.getById(item.getRestaurantId());

        FoodItemDto dto = modelMapper.map(item, FoodItemDto.class);
        dto.setRestaurantDto(restaurantDto);
        dto.setCategoryId(item.getFoodCategory().getId());

        return dto;
    }

    @Override
    public void delete(String id) {

        repository.deleteById(id);
    }
}