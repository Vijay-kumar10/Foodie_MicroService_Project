package com.food_service.service.impl;

import com.food_service.dto.FoodCategoryDto;
import com.food_service.entity.FoodCategory;
import com.food_service.repository.FoodCategoryRepo;
import com.food_service.service.FoodCategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodCategoryServiceImpl implements FoodCategoryService {

    private final FoodCategoryRepo repository;
    private final ModelMapper modelMapper;

    @Override
    public FoodCategoryDto create(FoodCategoryDto dto) {

        FoodCategory category = modelMapper.map(dto, FoodCategory.class);

        FoodCategory saved = repository.save(category);

        return modelMapper.map(saved, FoodCategoryDto.class);
    }

    @Override
    public List<FoodCategoryDto> getAll() {

        return repository.findAll()
                .stream()
                .map(category -> modelMapper.map(category, FoodCategoryDto.class))
                .toList();
    }

    @Override
    public FoodCategoryDto getById(String id) {

        FoodCategory category = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        return modelMapper.map(category, FoodCategoryDto.class);
    }

    @Override
    public void delete(String id) {

        repository.deleteById(id);
    }
}