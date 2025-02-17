package com.yassir.RateMe.Dto.Place;

import com.yassir.RateMe.Dto.Category.EmbeddedCategoryDTO;


import java.time.LocalDate;
import java.util.List;

public record PlaceResponseDTO(
        Long id,

        String name,
        String description,
        String photo,
        String address,
        Double latitude,
        Double longitude,
        Double averageRating,
        EmbeddedCategoryDTO category
) {
}
