package com.yassir.RateMe.Mapper;

import com.yassir.RateMe.Dto.Category.CategoryRequestDTO;
import com.yassir.RateMe.Dto.Category.CategoryResponseDTO;
import com.yassir.RateMe.Model.Entity.Category;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-03-01T09:15:46+0000",
    comments = "version: 1.6.2, compiler: javac, environment: Java 23.0.1 (Oracle Corporation)"
)
@Component
public class ICategoryMapperImpl implements ICategoryMapper {

    @Override
    public Category toEntity(CategoryRequestDTO CategoryRequestDTO) {
        if ( CategoryRequestDTO == null ) {
            return null;
        }

        Category category = new Category();

        category.setName( CategoryRequestDTO.name() );

        return category;
    }

    @Override
    public CategoryResponseDTO toResponseDto(Category category) {
        if ( category == null ) {
            return null;
        }

        Long id = null;
        String name = null;

        id = (long) category.getId();
        name = category.getName();

        CategoryResponseDTO categoryResponseDTO = new CategoryResponseDTO( id, name );

        return categoryResponseDTO;
    }
}
