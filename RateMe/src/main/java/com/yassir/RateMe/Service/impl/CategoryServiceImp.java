package com.yassir.RateMe.Service.impl;

import com.yassir.RateMe.Dto.Category.CategoryRequestDTO;
import com.yassir.RateMe.Dto.Category.CategoryResponseDTO;
import com.yassir.RateMe.Mapper.ICategoryMapper;
import com.yassir.RateMe.Model.Entity.Category;
import com.yassir.RateMe.Repository.CategoryRepository;
import com.yassir.RateMe.Service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;


@Service
public class CategoryServiceImp implements ICategoryService {


    private final CategoryRepository categoryRepository;
    private final ICategoryMapper categoryMapper;


    @Autowired
    public CategoryServiceImp(CategoryRepository categoryRepository, ICategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;

    }


    @Override
    public CategoryResponseDTO createCategory(CategoryRequestDTO categoryRequestDTO) {
        if (categoryRequestDTO.name() == null || categoryRequestDTO.name().trim().isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be empty");
        }
        if (categoryRequestDTO.name().length() > 255) {
            throw new IllegalArgumentException("Category name is too long");
        }
        Category category = categoryMapper.toEntity(categoryRequestDTO);
        Category savedCategory = categoryRepository.save(category);
        return categoryMapper.toResponseDto(savedCategory);
    }


    @Override
    public CategoryResponseDTO getCategoryById(Long categoryId) {
        if (categoryId == null || categoryId < 0) {
            throw new IllegalArgumentException("Invalid category ID: " + categoryId);
        }
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Category not found with ID: " + categoryId));
        return categoryMapper.toResponseDto(category);
    }

    @Override
    public CategoryResponseDTO updateCategory(Long id, CategoryRequestDTO categoryRequestDTO) {
        if (categoryRequestDTO.name() == null || categoryRequestDTO.name().trim().isEmpty()) {
            throw new IllegalArgumentException("Category name cannot be empty");
        }
        Category existingCategory = categoryRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category not found with ID: " + id));
        existingCategory.setName(categoryRequestDTO.name());

        Category updatedCategory = categoryRepository.save(existingCategory);
        return categoryMapper.toResponseDto(updatedCategory);
    }

    @Override
    public List<CategoryResponseDTO> getAllCategorys() {
        List<Category> categorys = (List<Category>) categoryRepository.findAll();
        return categorys.stream()
                .map(categoryMapper::toResponseDto)
                .collect(Collectors.toList());
    }


    @Override
    public void deleteCategory(Long categoryId) {
        if (!categoryRepository.existsById(categoryId)) {
            throw new IllegalArgumentException("Category not found with ID: " + categoryId);
        }
        categoryRepository.deleteById(categoryId);
    }




























}
