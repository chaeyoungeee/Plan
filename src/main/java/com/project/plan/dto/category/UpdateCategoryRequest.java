package com.project.plan.dto.category;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateCategoryRequest {
    @NotBlank(message = "변경할 카테고리명을 입력해주세요.")
    private String name;
    private String color;
}
