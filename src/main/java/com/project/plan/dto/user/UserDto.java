package com.project.plan.dto.user;

import com.project.plan.domain.User;
import com.project.plan.dto.plan.PlanDto;
import com.project.plan.dto.category.CategoryDto;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long userId;
    private String username;
    private String nickname;
    private List<PlanDto> plans;
    private List<CategoryDto> categories;
    private List<FriendResponse> friends;

    public UserDto(User user) {
        userId = user.getId();
        nickname = user.getNickname();
        friends = user.getFriends()
                .stream()
                .map(FriendResponse::new)
                .collect(Collectors.toList());
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
