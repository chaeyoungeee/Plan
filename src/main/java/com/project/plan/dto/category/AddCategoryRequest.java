package com.project.plan.dto.category;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AddCategoryRequest {
    private String name;
    private String color;
    private Long userId;
}
