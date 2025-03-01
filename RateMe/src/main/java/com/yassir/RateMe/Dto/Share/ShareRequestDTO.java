package com.yassir.RateMe.Dto.Share;

import lombok.Builder;

import java.time.LocalDateTime;

public record ShareRequestDTO(

       String title,
        Long userId,
        Long placeId


) {
}
