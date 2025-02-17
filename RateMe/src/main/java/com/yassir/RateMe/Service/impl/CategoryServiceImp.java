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


    private final CategoryRepository farmRepository;
    private final ICategoryMapper farmMapper;


    @Autowired
    public CategoryServiceImp(CategoryRepository farmRepository, ICategoryMapper farmMapper) {
        this.farmRepository = farmRepository;
        this.farmMapper = farmMapper;

    }


    @Override
    public CategoryResponseDTO createCategory(CategoryRequestDTO farmRequestDTO) {
        Category farm = farmMapper.toEntity(farmRequestDTO);
        Category savedCategory = farmRepository.save(farm);
        return farmMapper.toResponseDto(savedCategory);
    }


    @Override
    public CategoryResponseDTO getCategoryById(Long farmId) {
        Category farm = farmRepository.findById(farmId)
                .orElseThrow(() -> new IllegalArgumentException("Category not found with ID: " + farmId));
        return farmMapper.toResponseDto(farm);
    }

    @Override
    public CategoryResponseDTO updateCategory(Long id, CategoryRequestDTO farmRequestDTO) {
        Category existingCategory = farmRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Category not found with ID: " + id));
        existingCategory.setName(farmRequestDTO.name());

        Category updatedCategory = farmRepository.save(existingCategory);
        return farmMapper.toResponseDto(updatedCategory);
    }

    @Override
    public List<CategoryResponseDTO> getAllCategorys() {
        List<Category> farms = (List<Category>) farmRepository.findAll();
        return farms.stream()
                .map(farmMapper::toResponseDto)
                .collect(Collectors.toList());
    }


    @Override
    public void deleteCategory(Long farmId) {
        if (!farmRepository.existsById(farmId)) {
            throw new IllegalArgumentException("Category not found with ID: " + farmId);
        }
        farmRepository.deleteById(farmId);
    }


//    @Override
//    public List<CategoryResponseDTO> searchCategorys(String name, String location, Double minArea) {
//        Specification<Category> spec = CategorySpecification.searchCategorys(name, location, minArea);
//        List<Category> farms = farmRepository.findAll(spec);
//        return farms.stream()
//                .map(farmMapper::toResponseDto)
//                .collect(Collectors.toList());
//    }


    @Override
    public Page<CategoryResponseDTO> getCategorysPaginatedAndSorted(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Category> farmPage = farmRepository.findAll(pageable);
        return farmPage.map(farmMapper::toResponseDto);
    }
























}
