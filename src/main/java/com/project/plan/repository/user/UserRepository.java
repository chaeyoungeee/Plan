package com.project.plan.repository.user;

import com.project.plan.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository {
    void save(User user);
    User findById(Long id);
    Optional<User> findByUsername(String username);
    Optional<User> findByNickname(String nickname);
    List<User> findAll();

}
