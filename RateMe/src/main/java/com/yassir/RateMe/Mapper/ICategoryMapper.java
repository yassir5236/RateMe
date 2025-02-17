    package com.yassir.RateMe.Mapper;

    import com.yassir.RateMe.Dto.Category.CategoryRequestDTO;
    import com.yassir.RateMe.Dto.Category.CategoryResponseDTO;
    import com.yassir.RateMe.Model.Entity.Category;
    import org.mapstruct.Mapper;

    @Mapper(componentModel = "spring")
    public interface ICategoryMapper {
        Category toEntity (CategoryRequestDTO CategoryRequestDTO);
        CategoryResponseDTO toResponseDto (Category category);
    }



