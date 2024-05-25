package com.project.plan.dto.user;

import com.project.plan.domain.User;
import com.project.plan.dto.plan.PlanDto;
import com.project.plan.dto.category.CategoryDto;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserDto {
    private Long userId;
    @NotNull
    private String username;
    @NotNull
    private String nickname;
    private List<PlanDto> plans;
    private List<CategoryDto> categories;

    public UserDto(User user) {
        userId = user.getId();
        nickname = user.getNickname();
        plans = user.getPlans()
                .stream()
                .map(PlanDto::new)
                .collect(Collectors.toList());
        categories = user.getCategories()
                .stream()
                .map(CategoryDto::new)
                .collect(Collectors.toList());
    }
}
