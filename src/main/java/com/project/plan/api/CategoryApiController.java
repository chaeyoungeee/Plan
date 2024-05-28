package com.project.plan.api;

import com.project.plan.domain.Category;
import com.project.plan.domain.Plan;
import com.project.plan.dto.category.AddCategoryRequest;
import com.project.plan.dto.category.CategoryDto;
import com.project.plan.dto.plan.PlanDto;
import com.project.plan.service.category.CategoryService;
import com.project.plan.service.category.CategoryServiceImpl;
import com.project.plan.service.plan.PlanService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryApiController {
    private final CategoryService categoryService;
    private final PlanService planService;

    //모든 카테고리 조회
    @GetMapping("/categories")
    public Result category() {
        List<Category> categories = categoryService.findAll();
        List<CategoryDto> collect = categories.stream()
                .map(CategoryDto::new)
                .toList();
        return new Result(collect);
    }

    @PostMapping("/category")
    public CategoryDto addCategory(@RequestBody AddCategoryRequest category) {
        return categoryService.save(category);
    }

    @DeleteMapping("/category/{categoryId}")
    public void deleteCategory(@PathVariable("categoryId") Long categoryId) {
        categoryService.delete(categoryId);
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }

}
