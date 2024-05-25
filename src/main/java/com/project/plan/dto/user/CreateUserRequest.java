package com.project.plan.dto.user;

import lombok.Data;

@Data
public class CreateUserRequest {
    String username;
    String nickname;
    String password;
}
