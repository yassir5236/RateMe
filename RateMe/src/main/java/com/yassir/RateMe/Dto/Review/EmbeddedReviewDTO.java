package com.yassir.RateMe.Dto.Review;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record EmbeddedReviewDTO(
         Long id,
         Double rating,
         @NotNull(message = "comment name cannot be empty")
         String comment,
         LocalDateTime createdDate
) {
}
