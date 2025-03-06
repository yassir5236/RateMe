package com.yassir.RateMe.Dto.Like;


import com.yassir.RateMe.Dto.Place.EmbeddedPlaceDTO;
import com.yassir.RateMe.Dto.Place.PlaceResponseDTO;
import com.yassir.RateMe.Dto.User.EmbeddedUserDTO;

public record LikeResponseDTO(
        Long id,
        EmbeddedUserDTO user,
        EmbeddedPlaceDTO place
) {
}
