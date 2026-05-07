package com.food_service.service;

import com.food_service.dto.FoodCategoryDto;

import java.util.List;

public interface FoodCategoryService {

    FoodCategoryDto create(FoodCategoryDto dto);

    List<FoodCategoryDto> getAll();

    FoodCategoryDto getById(String id);

    void delete(String id);
}
