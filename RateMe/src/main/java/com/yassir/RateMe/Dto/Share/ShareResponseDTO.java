package com.yassir.RateMe.Dto.Share;


import com.yassir.RateMe.Dto.Place.EmbeddedPlaceDTO;
import com.yassir.RateMe.Dto.Place.PlaceResponseDTO;
import com.yassir.RateMe.Dto.User.EmbeddedUserDTO;

import java.time.LocalDateTime;

public record ShareResponseDTO(
        Long id,
        String title,
        EmbeddedUserDTO user,
//        EmbeddedPlaceDTO place
        PlaceResponseDTO place

) {
}
