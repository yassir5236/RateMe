package com.yassir.RateMe.Service.impl;

import com.yassir.RateMe.Dto.Category.CategoryRequestDTO;
import com.yassir.RateMe.Dto.Category.CategoryResponseDTO;
import com.yassir.RateMe.Mapper.ICategoryMapper;
import com.yassir.RateMe.Model.Entity.Category;
import com.yassir.RateMe.Repository.CategoryRepository;
import com.yassir.RateMe.Service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
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
        Category category = categoryMapper.toEntity(categoryRequestDTO);
        Category savedCategory = categoryRepository.save(category);
        return categoryMapper.toResponseDto(savedCategory);
    }


    @Override
    public CategoryResponseDTO getCategoryById(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Category not found with ID: " + categoryId));
        return categoryMapper.toResponseDto(category);
    }

    @Override
    public CategoryResponseDTO updateCategory(Long id, CategoryRequestDTO categoryRequestDTO) {
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


//    @Override
//    public List<CategoryResponseDTO> searchCategorys(String name, String location, Double minArea) {
//        Specification<Category> spec = CategorySpecification.searchCategorys(name, location, minArea);
//        List<Category> categorys = categoryRepository.findAll(spec);
//        return categorys.stream()
//                .map(categoryMapper::toResponseDto)
//                .collect(Collectors.toList());
//    }


    @Override
    public Page<CategoryResponseDTO> getCategorysPaginatedAndSorted(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Category> categoryPage = categoryRepository.findAll(pageable);
        return categoryPage.map(categoryMapper::toResponseDto);
    }
























}
