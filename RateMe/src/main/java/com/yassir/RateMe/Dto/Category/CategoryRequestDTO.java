package com.yassir.RateMe.Dto.Category;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record CategoryRequestDTO(

        @NotBlank(message = "Farm name cannot be empty")
        String name


) {
}
