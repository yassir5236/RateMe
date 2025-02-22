package com.yassir.RateMe.Dto.Review;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record EmbeddedReviewDTO(
         Long id,
         Double rating,
         String comment,
         LocalDateTime createdDate
) {
}
