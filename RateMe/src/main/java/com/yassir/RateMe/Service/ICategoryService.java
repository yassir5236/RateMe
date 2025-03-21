package com.yassir.RateMe.Service;

import com.yassir.RateMe.Dto.Category.CategoryRequestDTO;
import com.yassir.RateMe.Dto.Category.CategoryResponseDTO;
import org.springframework.data.domain.Page;


import java.util.List;

public interface ICategoryService {
    CategoryResponseDTO createCategory(CategoryRequestDTO categoryRequestDTO);

    CategoryResponseDTO getCategoryById(Long categoryId);

    CategoryResponseDTO updateCategory(Long id, CategoryRequestDTO categoryRequestDTO);

    List<CategoryResponseDTO> getAllCategorys();

    void deleteCategory(Long categoryId);


}
