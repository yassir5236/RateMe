package com.yassir.RateMe.Dto.Place;


import java.time.LocalDate;
import java.util.List;

public record EmbeddedPlaceDTO(
         int id,

         String name,
         String description,
         String photo,
         String address,
         Double latitude,
         Double longitude,
         Double averageRating

) {
}
