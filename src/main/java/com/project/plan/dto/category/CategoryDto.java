package com.project.plan.dto.category;

import com.project.plan.domain.Category;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CategoryDto {
    private Long categoryId;
    private String name;
    private String color;
    private Long userId;

    public CategoryDto(Category category) {
        categoryId = category.getId();
        name = category.getName();
        color = category.getColor();
        if (category.getUser() == null) {
            userId = null;
        } else {
            userId = category.getUser().getId();
        }

    }
}
