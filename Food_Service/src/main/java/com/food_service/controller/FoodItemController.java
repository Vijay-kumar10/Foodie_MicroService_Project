package com.food_service.controller;

import com.food_service.dto.FoodItemDto;
import com.food_service.service.FoodItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/food-items")
@RequiredArgsConstructor
public class FoodItemController {

    private final FoodItemService service;

    @PostMapping
    public FoodItemDto create(@RequestBody FoodItemDto dto) {
        return service.create(dto);
    }

    @GetMapping
    public Page<FoodItemDto> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {

        return service.getAll(page, size);
    }

    @GetMapping("/{id}")
    public FoodItemDto getById(@PathVariable String id) {

        return service.getById(id);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id) {

        service.delete(id);

        return "Deleted Successfully";
    }
}