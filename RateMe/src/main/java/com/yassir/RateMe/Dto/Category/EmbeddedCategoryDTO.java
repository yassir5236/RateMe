package com.yassir.RateMe.Dto.Category;

import java.time.LocalDate;

public record EmbeddedCategoryDTO(
         Long id,

         String name,
         String location,
         double totalArea ,
         LocalDate created
) {
}
