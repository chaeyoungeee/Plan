package com.project.plan.api;


import com.project.plan.domain.User;
import com.project.plan.dto.user.*;
import com.project.plan.service.user.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserApiController {
    private final UserServiceImpl userService;

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
        Optional<User> user = userService.findByUsername(req.getUsername()).stream().findFirst();

//        log.info(String.valueOf(user.get().getPlans().size()));



        if (user.isPresent()) {
            User u = user.get();
            if (req.getPassword().equals(u.getPassword())) {
                return new UserDto(u);
            }
        }
        throw new AuthenticationException("아이디나 비밀번호가 틀렸습니다.");
    }

    @PostMapping("/friend")
    public FriendResponse addFriend(@RequestBody AddFriendRequest request) {
         FriendResponse r=  userService.addFriend(request.getId(), request.getNickname());

         log.info(userService.findById(1L).get().getFriends().toString());

         return  r;
    }

}
