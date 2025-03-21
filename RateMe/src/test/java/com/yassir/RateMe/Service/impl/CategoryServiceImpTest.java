package com.yassir.RateMe.Service.impl;

import com.yassir.RateMe.Dto.Category.CategoryRequestDTO;
import com.yassir.RateMe.Dto.Category.CategoryResponseDTO;
import com.yassir.RateMe.Mapper.ICategoryMapper;
import com.yassir.RateMe.Model.Entity.Category;
import com.yassir.RateMe.Repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CategoryServiceImpTest {

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private ICategoryMapper categoryMapper;

    @InjectMocks
    private CategoryServiceImp categoryService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Test Create Category
    @Test
    void testCreateCategory() {
        CategoryRequestDTO requestDTO = CategoryRequestDTO.builder().name("Electronics").build();
        Category category = new Category();
        category.setName("Electronics");

        Category savedCategory = new Category();
        savedCategory.setId(1L);
        savedCategory.setName("Electronics");

        CategoryResponseDTO responseDTO = new CategoryResponseDTO(1L, "Electronics");

        when(categoryMapper.toEntity(requestDTO)).thenReturn(category);
        when(categoryRepository.save(category)).thenReturn(savedCategory);
        when(categoryMapper.toResponseDto(savedCategory)).thenReturn(responseDTO);

        CategoryResponseDTO result = categoryService.createCategory(requestDTO);

        assertNotNull(result);
        assertEquals(1L, result.id());
        assertEquals("Electronics", result.name());

        verify(categoryMapper).toEntity(requestDTO);
        verify(categoryRepository).save(category);
        verify(categoryMapper).toResponseDto(savedCategory);
    }

    @Test
    void testCreateCategory_WithEmptyName_ShouldThrowException() {
        CategoryRequestDTO requestDTO = CategoryRequestDTO.builder().name("").build();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            categoryService.createCategory(requestDTO);
        });

        assertEquals("Category name cannot be empty", exception.getMessage());
    }



    @Test
    void testCreateCategory_WithLongName_ShouldThrowException() {
        String longName = "A".repeat(256);
        CategoryRequestDTO requestDTO = CategoryRequestDTO.builder().name(longName).build();

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            categoryService.createCategory(requestDTO);
        });

        assertEquals("Category name is too long", exception.getMessage());
    }

    @Test
    void testGetCategoryById() {
        Long categoryId = 1L;
        Category category = new Category();
        category.setId(categoryId);
        category.setName("Books");

        CategoryResponseDTO responseDTO = new CategoryResponseDTO(categoryId, "Books");

        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));
        when(categoryMapper.toResponseDto(category)).thenReturn(responseDTO);

        CategoryResponseDTO result = categoryService.getCategoryById(categoryId);

        assertNotNull(result);
        assertEquals(categoryId, result.id());
        assertEquals("Books", result.name());

        verify(categoryRepository).findById(categoryId);
        verify(categoryMapper).toResponseDto(category);
    }

    @Test
    void testGetCategoryById_NegativeId_ShouldThrowException() {
        Long categoryId = -1L;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            categoryService.getCategoryById(categoryId);
        });

        assertEquals("Invalid category ID: " + categoryId, exception.getMessage());
    }

    @Test
    void testGetCategoryById_RecentlyAdded() {
        Long categoryId = 1L;
        Category category = new Category();
        category.setId(categoryId);
        category.setName("Newly Added");

        CategoryResponseDTO responseDTO = new CategoryResponseDTO(categoryId, "Newly Added");

        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(category));
        when(categoryMapper.toResponseDto(category)).thenReturn(responseDTO);

        CategoryResponseDTO result = categoryService.getCategoryById(categoryId);

        assertNotNull(result);
        assertEquals(categoryId, result.id());
        assertEquals("Newly Added", result.name());
    }

    @Test
    void testUpdateCategory() {
        Long categoryId = 1L;
        CategoryRequestDTO requestDTO = CategoryRequestDTO.builder().name("Updated Name").build();

        Category existingCategory = new Category();
        existingCategory.setId(categoryId);
        existingCategory.setName("Old Name");

        Category updatedCategory = new Category();
        updatedCategory.setId(categoryId);
        updatedCategory.setName("Updated Name");

        CategoryResponseDTO responseDTO = new CategoryResponseDTO(categoryId, "Updated Name");

        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(existingCategory));
        when(categoryRepository.save(existingCategory)).thenReturn(updatedCategory);
        when(categoryMapper.toResponseDto(updatedCategory)).thenReturn(responseDTO);

        CategoryResponseDTO result = categoryService.updateCategory(categoryId, requestDTO);

        assertNotNull(result);
        assertEquals("Updated Name", result.name());

        verify(categoryRepository).findById(categoryId);
        verify(categoryRepository).save(existingCategory);
        verify(categoryMapper).toResponseDto(updatedCategory);
    }





    @Test
    void testUpdateCategory_WithEmptyName_ShouldThrowException() {
        Long categoryId = 1L;
        CategoryRequestDTO requestDTO = CategoryRequestDTO.builder().name("").build();
        Category existingCategory = new Category();
        existingCategory.setId(categoryId);
        existingCategory.setName("Old Name");

        when(categoryRepository.findById(categoryId)).thenReturn(Optional.of(existingCategory));

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            categoryService.updateCategory(categoryId, requestDTO);
        });

        assertEquals("Category name cannot be empty", exception.getMessage());
    }

    @Test
    void testUpdateCategory_NonExisting_ShouldThrowException() {
        Long categoryId = 99L;
        CategoryRequestDTO requestDTO = CategoryRequestDTO.builder().name("New Name").build();

        when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            categoryService.updateCategory(categoryId, requestDTO);
        });

        assertEquals("Category not found with ID: " + categoryId, exception.getMessage());
    }






    @Test
    void testGetAllCategories() {
        Category category1 = new Category();
        category1.setId(1L);
        category1.setName("Books");

        Category category2 = new Category();
        category2.setId(2L);
        category2.setName("Electronics");

        List<Category> categories = Arrays.asList(category1, category2);

        CategoryResponseDTO dto1 = new CategoryResponseDTO(1L, "Books");
        CategoryResponseDTO dto2 = new CategoryResponseDTO(2L, "Electronics");

        when(categoryRepository.findAll()).thenReturn(categories);
        when(categoryMapper.toResponseDto(category1)).thenReturn(dto1);
        when(categoryMapper.toResponseDto(category2)).thenReturn(dto2);

        List<CategoryResponseDTO> result = categoryService.getAllCategorys();

        assertEquals(2, result.size());
        assertEquals("Books", result.get(0).name());
        assertEquals("Electronics", result.get(1).name());

        verify(categoryRepository).findAll();
        verify(categoryMapper).toResponseDto(category1);
        verify(categoryMapper).toResponseDto(category2);
    }

    @Test
    void testDeleteCategory() {
        Long categoryId = 1L;

        when(categoryRepository.existsById(categoryId)).thenReturn(true);
        doNothing().when(categoryRepository).deleteById(categoryId);

        categoryService.deleteCategory(categoryId);

        verify(categoryRepository).existsById(categoryId);
        verify(categoryRepository).deleteById(categoryId);
    }


    @Test
    void testDeleteCategory_NotExisting_ShouldThrowException() {
        Long categoryId = 100L;

        when(categoryRepository.existsById(categoryId)).thenReturn(false);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            categoryService.deleteCategory(categoryId);
        });

        assertEquals("Category not found with ID: " + categoryId, exception.getMessage());
    }

    @Test
    void testDeleteCategory_VerifyDeletion() {
        Long categoryId = 1L;

        when(categoryRepository.existsById(categoryId)).thenReturn(true);
        doNothing().when(categoryRepository).deleteById(categoryId);

        categoryService.deleteCategory(categoryId);

        verify(categoryRepository, times(1)).existsById(categoryId);
        verify(categoryRepository, times(1)).deleteById(categoryId);
    }

    @Test
    void testDeleteCategory_UsedInAnotherObject_ShouldThrowException() {
        Long categoryId = 2L;

        when(categoryRepository.existsById(categoryId)).thenReturn(true);
        doThrow(new RuntimeException("Cannot delete category as it is referenced in another table"))
                .when(categoryRepository).deleteById(categoryId);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            categoryService.deleteCategory(categoryId);
        });

        assertEquals("Cannot delete category as it is referenced in another table", exception.getMessage());
    }

    @Test
    void testDeleteCategory_NotFound() {
        Long categoryId = 1L;

        when(categoryRepository.existsById(categoryId)).thenReturn(false);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            categoryService.deleteCategory(categoryId);
        });

        assertEquals("Category not found with ID: " + categoryId, exception.getMessage());

        verify(categoryRepository).existsById(categoryId);
        verify(categoryRepository, never()).deleteById(categoryId);
    }

    @Test
    void testGetCategoryById_NotFound() {
        Long categoryId = 1L;

        when(categoryRepository.findById(categoryId)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            categoryService.getCategoryById(categoryId);
        });

        assertEquals("Category not found with ID: " + categoryId, exception.getMessage());

        verify(categoryRepository).findById(categoryId);
        verify(categoryMapper, never()).toResponseDto(any());
    }
}
