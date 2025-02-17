package com.yassir.RateMe.Dto.Place;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record PlaceRequestDTO(



        String name,
        String description,
        String photo,
        String address,
        Double latitude,
        Double longitude,
        Double averageRating,
        Long categoryId
) {
}
