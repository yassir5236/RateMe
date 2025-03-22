package com.yassir.RateMe.Dto.Place;

import com.yassir.RateMe.Model.Entity.User;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

public record PlaceRequestDTO(
        @NotBlank(message = "place name cannot be empty")
        String name,
        @NotBlank(message = "description name cannot be empty")
        String description,
        @NotBlank(message = "address name cannot be empty")
        String address,
        Double latitude,
        Double longitude,

        Long categoryId,
        Long userId
) {
}
