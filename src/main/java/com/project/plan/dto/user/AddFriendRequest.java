package com.project.plan.dto.user;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddFriendRequest {
    private Long id;
    private String nickname;
}
