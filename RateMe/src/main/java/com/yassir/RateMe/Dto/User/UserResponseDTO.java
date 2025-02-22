package com.yassir.RateMe.Dto.User;

import com.yassir.RateMe.Dto.Category.EmbeddedCategoryDTO;
import com.yassir.RateMe.Dto.Place.EmbeddedPlaceDTO;
import com.yassir.RateMe.Dto.image.ImageResponseDTO;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record UserResponseDTO(
        Long id,
        String username,
        String email,
        String password,
        String location,
        String profilePicture,
        String bio,
        String role,
        EmbeddedCategoryDTO category,
        EmbeddedPlaceDTO place
        ){}
