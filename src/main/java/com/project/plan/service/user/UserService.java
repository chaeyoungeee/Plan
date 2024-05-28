package com.project.plan.service.user;

import com.project.plan.domain.User;
import com.project.plan.dto.user.FriendResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    public Long join(User user);

    Optional<User> findByUsername(String username);
    Optional<User> findByNickname(String nickname);

    public FriendResponse addFriend(Long id, String nickname);
    Optional<User> findById(Long id);
    List<User> findUsers();
    void updateNickname(Long id, String nickname);
}
