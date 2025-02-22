package com.yassir.RateMe.Dto.Review;


import com.yassir.RateMe.Dto.Place.EmbeddedPlaceDTO;
import com.yassir.RateMe.Dto.User.EmbeddedUserDTO;

import java.time.LocalDateTime;

public record ReviewResponseDTO(
        Long id,
        Double rating,
        String comment,
        LocalDateTime createdDate,
        EmbeddedUserDTO user,
        EmbeddedPlaceDTO place

) {
}
