package com.yassir.RateMe.Dto.User;

import com.yassir.RateMe.Dto.image.ImageResponseDTO;
import jakarta.validation.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record UserRequestDTO(
        @NotBlank(message = "username name cannot be empty")
        String username,
        @NotBlank(message = "email name cannot be empty")
        String email,
        @NotBlank(message = "password name cannot be empty")
        String password,
        String location,
        String profilePicture,
        String bio,
        String role
) {
}
