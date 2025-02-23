package com.yassir.RateMe.Dto.Review;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record ReviewRequestDTO(

        Double rating,
        String comment,
        LocalDateTime createdDate,
        Long userId,
        Long placeId


) {
}
