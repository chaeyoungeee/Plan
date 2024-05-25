package com.project.plan.service.category;

import com.project.plan.domain.Category;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CategoryService {
    void save(Category category);
    public List<Category> findAll();
    public Optional<Category> findById(Long id);
    public void delete(Long id);

    public void update(Long id, String name, String color);
}
