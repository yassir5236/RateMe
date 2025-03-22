package com.yassir.RateMe.Dto.Share;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

import java.time.LocalDateTime;

public record ShareRequestDTO(
        @NotBlank(message = "title name cannot be empty")
       String title,
        Long userId,
        Long placeId


) {
}
