package com.yassir.RateMe.Dto.Share;

import java.time.LocalDateTime;

public record EmbeddedShareDTO(
         Long id,
         String title,
         Long userId,
         Long placeId
) {
}
