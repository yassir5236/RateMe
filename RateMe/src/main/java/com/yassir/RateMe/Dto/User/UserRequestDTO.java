package com.yassir.RateMe.Dto.User;

import com.yassir.RateMe.Dto.image.ImageResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record UserRequestDTO(
        String username,
        String email,
        String password,
        String location,
        String profilePicture,
        String bio,
        String role
) {
}
