package com.project.plan.dto.plan;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdatePlanRequest {
    private String title;
    private String start;
    private String end;
}
