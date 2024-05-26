package com.project.plan.domain;

import com.project.plan.dto.user.UserDto;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Entity
@Table(name = "member")
@Data
public class User {
    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    private String username;
    private String nickname;
    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Plan> plans = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "master_id")
    private User master;

    @OneToMany(mappedBy = "master", cascade = CascadeType.ALL)
    private List<User> following = new ArrayList<>();;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Category> categories = new ArrayList<>();;


    public static User createUser(String username, String nickname, String password) {
        User user = new User();
        user.setUsername(username);
        user.setNickname(nickname);
        user.setPassword(password);

        return user;
    }

    public UserDto toDto() {
        return new UserDto(this);
    }

}
