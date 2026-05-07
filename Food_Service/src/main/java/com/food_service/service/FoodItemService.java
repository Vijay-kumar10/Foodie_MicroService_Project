package com.food_service.service;

import com.food_service.dto.FoodItemDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface FoodItemService {

    FoodItemDto create(FoodItemDto dto);

    Page<FoodItemDto> getAll(int page, int size);

    FoodItemDto getById(String id);

    void delete(String id);
}
