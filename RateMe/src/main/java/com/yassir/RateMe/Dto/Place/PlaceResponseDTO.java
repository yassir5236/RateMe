package com.yassir.RateMe.Dto.Place;

import com.yassir.RateMe.Dto.Category.EmbeddedCategoryDTO;
import com.yassir.RateMe.Dto.User.EmbeddedUserDTO;
import com.yassir.RateMe.Dto.image.ImageResponseDTO;
import com.yassir.RateMe.Model.Entity.Image;
import org.springframework.web.multipart.MultipartFile;


import java.time.LocalDate;
import java.util.List;

public record PlaceResponseDTO(
        Long id,

        String name,
        String description,
        List<ImageResponseDTO> images,
        String address,
        Double latitude,
        Double longitude,
        Double averageRating,
        EmbeddedUserDTO user,
        EmbeddedCategoryDTO category
) {
}
