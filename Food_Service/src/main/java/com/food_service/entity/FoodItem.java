package com.food_service.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class FoodItem {

    @Id
    private String id;

    private String title;

    private String description;

    private int quantity;

    @Enumerated(EnumType.STRING)
    private FoodType foodType = FoodType.VEG;

    private boolean isOutOfStock = true;

    @ManyToOne
    private FoodCategory foodCategory;

    //store the Restaurant Item
    @Column(nullable = false)
    private String restaurantId;
}
