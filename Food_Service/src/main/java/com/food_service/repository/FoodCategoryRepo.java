package com.food_service.repository;

import com.food_service.entity.FoodCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodCategoryRepo extends JpaRepository<FoodCategory, String> {
}
