package com.project.plan.repository.category;

import com.project.plan.domain.Category;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository {
    void save(Category category);
    Category findById(Long id);
    List<Category> findAll();
    void delete(Long id);

}
