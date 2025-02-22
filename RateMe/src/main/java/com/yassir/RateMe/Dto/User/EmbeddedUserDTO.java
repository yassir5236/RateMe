package com.yassir.RateMe.Dto.User;


import com.yassir.RateMe.Dto.image.ImageResponseDTO;

import java.util.List;

public record EmbeddedUserDTO(
        Long id,
        String username,
        String email,
        String password,
        String location,
        String profilePicture,
        String bio,
        String role

) {
}
