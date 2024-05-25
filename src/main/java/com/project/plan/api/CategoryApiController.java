package com.project.plan.api;

import com.project.plan.domain.Category;
import com.project.plan.dto.category.CategoryDto;
import com.project.plan.service.category.CategoryServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CategoryApiController {
    private final CategoryServiceImpl categoryService;

    //모든 카테고리 조회
    @GetMapping("/categories")
    public Result category() {
        List<Category> categories = categoryService.findAll();
        List<CategoryDto> collect = categories.stream()
                .map(CategoryDto::new)
                .toList();
        return new Result(collect);
    }

    @DeleteMapping("/category/{categoryId}")
    public String deleteCategory(@PathVariable("categoryId") Long categoryId) {
        categoryService.delete(categoryId);
        return "done";
    }

    @Data
    @AllArgsConstructor
    static class Result<T> {
        private T data;
    }

}
