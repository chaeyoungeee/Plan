package com.project.plan.service.category;

import com.project.plan.domain.Category;
import com.project.plan.dto.category.AddCategoryRequest;
import com.project.plan.dto.category.CategoryDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CategoryService {
    CategoryDto save(Category category);

    CategoryDto save(AddCategoryRequest categoryRequest);
    public List<Category> findAll();
    public Optional<Category> findById(Long id);
    public void delete(Long id);

    public void update(Long id, String name, String color);
}
