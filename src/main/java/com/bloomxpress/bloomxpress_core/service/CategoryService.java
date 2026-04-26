package com.bloomxpress.bloomxpress_core.service;

import com.bloomxpress.bloomxpress_core.dto.request.CategoryDTO;
import com.bloomxpress.bloomxpress_core.entity.Category;
import com.bloomxpress.bloomxpress_core.mapper.CategoryMapper;
import com.bloomxpress.bloomxpress_core.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public List<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream().map(categoryMapper::toDTO).toList();
    }

    @Transactional
    public Category createCategory(CategoryDTO categoryDTO) {
        // Reset ID to ensure it's a creation, not an accidental update
        Category category = categoryMapper.toEntity(categoryDTO);
        category.setId(null);
        return categoryRepository.save(category);
    }

    @Transactional
    public Optional<CategoryDTO> updateCategory(Long id, Category categoryDetails) {
        return categoryRepository.findById(id)
                .map(existingCategory -> {
                    existingCategory.setName(categoryDetails.getName());
                    return categoryRepository.save(existingCategory);
                })
                .map(categoryMapper::toDTO);
    }
}