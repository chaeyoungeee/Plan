package com.project.plan.dto.category;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AddCategoryRequest {
    @NotBlank(message = "카테고리명을 입력해주세요.")
    private String name;
    private String color;
    private Long userId;
}
