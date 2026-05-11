package com.food_service.service.external;

import com.food_service.config.AppConstant;
import com.food_service.dto.RestaurantDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@RequiredArgsConstructor
public class RestWebClientService {

    private final WebClient.Builder webClient;

    public RestaurantDto getById(String id) {
            RestaurantDto restaurantDto =  webClient.baseUrl(AppConstant.RESTAURANT_SERVICE_URL)
                    .build().get().uri("/api/v1/restaurants/{id}",id)
                    .retrieve().bodyToMono(RestaurantDto.class).block();

            return restaurantDto;
    }

}
