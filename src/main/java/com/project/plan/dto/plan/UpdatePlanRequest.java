package com.project.plan.dto.plan;

import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdatePlanRequest {
    @NotBlank(message = "계획을 입력해주세요.")
    private String title;
    private String start;
    private String end;
}
