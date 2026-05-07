package com.food_service.controller;

import com.food_service.dto.FoodCategoryDto;
import com.food_service.service.FoodCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class FoodCategoryController {

    private final FoodCategoryService service;

    @PostMapping
    public FoodCategoryDto create(@RequestBody FoodCategoryDto dto) {

        return service.create(dto);
    }

    @GetMapping
    public List<FoodCategoryDto> getAll() {

        return service.getAll();
    }

    @GetMapping("/{id}")
    public FoodCategoryDto getById(@PathVariable String id) {

        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id) {

        service.delete(id);

        return "Deleted Successfully";
    }
}