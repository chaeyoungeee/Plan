package com.project.plan.dto.plan;

import com.project.plan.domain.Plan;
import com.project.plan.domain.PlanStatus;
import com.project.plan.dto.category.CategoryDto;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlanDto {
    private Long planId;
    private Long userId;
    private CategoryDto category;
    private String title;
    private String start;
    private String end;
    private PlanStatus status;
    private String color;

    public PlanDto(Plan plan) {
        planId = plan.getId();
        userId = plan.getUser().getId();
        category = new CategoryDto(plan.getCategory());
        color = category.getColor();
        title = plan.getTitle();
        start = plan.getStart();
        end = plan.getEnd();
        status = plan.getStatus();

    }
}
