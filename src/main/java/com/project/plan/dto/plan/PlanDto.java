package com.project.plan.dto.plan;

import com.project.plan.domain.Plan;
import com.project.plan.domain.PlanStatus;
import com.project.plan.domain.User;
import com.project.plan.dto.category.CategoryDto;
import com.project.plan.service.user.UserService;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class PlanDto {
    private Long planId;
    private Long userId;
    private Long categoryId;
    private String categoryName;
    private String title;
    private String start;
    private String end;
    private PlanStatus status;
    private String color;

    public PlanDto(Plan plan) {
        planId = plan.getId();
        userId = plan.getUser().getId();
        categoryId = plan.getCategory().getId();
        categoryName = plan.getCategory().getName();
        color = plan.getCategory().getColor();
        title = plan.getTitle();
        start = plan.getStart();
        end = plan.getEnd();
        status = plan.getStatus();
    }
}
