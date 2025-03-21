package com.yassir.RateMe.Dto.Place;


import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

public record EmbeddedPlaceDTO(
         Long id,

         String name,
         String description,
         List<String> photos,
         String address,
         Double latitude,
         Double longitude


) {
    public EmbeddedPlaceDTO(Long id, String name) {
        this(id, name, null, null, null, null, null);
    }
}
