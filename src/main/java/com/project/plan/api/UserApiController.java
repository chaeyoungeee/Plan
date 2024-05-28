package com.project.plan.api;


import com.project.plan.domain.User;
import com.project.plan.dto.plan.PlanDto;
import com.project.plan.dto.user.*;
import com.project.plan.service.plan.PlanService;
import com.project.plan.service.user.UserService;
import com.project.plan.service.user.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserApiController {
    private final UserService userService;
    private final PlanService planService;

    @PostMapping("/signup")
    public CreateUserResponse saveUser(@RequestBody @Valid CreateUserRequest req) {
        User user = new User();
        user.setUsername(req.getUsername());
        user.setPassword(req.getPassword());
        user.setNickname(req.getNickname());

        Long id = userService.join(user);

        System.out.println("req.getUsername() = " + req.getUsername());

        return new CreateUserResponse(id);
    }

    @PostMapping("/login")
    public UserDto login(@RequestBody @Valid CreateUserRequest req) throws AuthenticationException {
        User user = userService.findByUsername(req.getUsername())
                .stream()
                .findFirst()
                .orElseThrow(() -> new AuthenticationException("존재하지 않는 아이디입니다."));
        if (req.getPassword().equals(user.getPassword())) {
            return new UserDto(user);
        }
        throw new AuthenticationException("아이디나 비밀번호가 틀렸습니다.");
    }

    @PostMapping("/friend")
    public FriendResponse addFriend(@RequestBody AddFriendRequest request) {
         return  userService.addFriend(request.getId(), request.getNickname());
    }

    @GetMapping("/friend/{friendId}")
    public List<PlanDto> getFriendPlan(@PathVariable("friendId") Long friendId) {
        return planService.findByMemberId(friendId);
    }

}
