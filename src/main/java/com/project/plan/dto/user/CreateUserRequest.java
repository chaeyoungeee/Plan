package com.project.plan.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateUserRequest {
    @NotBlank(message = "username을 입력해주세요.")
    String username;
    String nickname;
    @NotBlank(message = "password를 입력해주세요.")
    @Size(min=4, message = "password는 4자 이상이여야 합니다.")
    String password;
}
