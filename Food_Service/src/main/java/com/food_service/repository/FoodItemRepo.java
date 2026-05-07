package com.food_service.repository;

import com.food_service.entity.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodItemRepo  extends JpaRepository<FoodItem, String> {
}
