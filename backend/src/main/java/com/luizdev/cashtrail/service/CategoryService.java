package com.luizdev.cashtrail.service;

import com.luizdev.cashtrail.dto.CategoryRequest;
import com.luizdev.cashtrail.model.Category;
import com.luizdev.cashtrail.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) { this.categoryRepository = categoryRepository; }

    public Category create(CategoryRequest request) {
        Category category = new Category();
        category.setName(request.name());
        return categoryRepository.save(category);
    }

    public List<Category> findAll() { return categoryRepository.findAll(); }
}