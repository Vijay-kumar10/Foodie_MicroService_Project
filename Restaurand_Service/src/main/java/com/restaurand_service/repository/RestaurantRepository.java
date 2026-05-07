package com.restaurand_service.repository;

import com.restaurand_service.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface  RestaurantRepository  extends JpaRepository<Restaurant, String> {

    List<Restaurant> findByName(String name);
    List<Restaurant> findByAddress(String address);

    List<Restaurant> findByNameContainingIgnoreCase(String name);
}
