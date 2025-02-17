package com.yassir.RateMe.Dto;

import org.springframework.web.multipart.MultipartFile;

public record UserRequestDto(
        String location,
        String bio,
        MultipartFile profilePicture
) {
}
