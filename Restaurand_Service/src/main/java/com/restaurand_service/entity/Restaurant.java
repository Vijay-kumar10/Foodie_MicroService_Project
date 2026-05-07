package com.restaurand_service.entity;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.*;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant {

    @Id
    private String id;
    private String name;
    private String address;
    private String phone;
    private String picture;

    @ElementCollection
    private List<String> pictures = new ArrayList<>();    //priority 1
    private boolean open  = false;

    //priority 2
    private LocalTime openTime;
    private LocalTime closeTime;

    @Lob
    private String aboutRestaurant;

}
