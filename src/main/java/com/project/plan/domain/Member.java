package com.project.plan.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table
@Getter
@Setter
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String username;
    private String nickname;
    private String password;

    @OneToMany(mappedBy = "plan")
    private List<Plan> plans;

    @ManyToOne
    @JoinColumn(name = "master_id")
    private Member master;

    @OneToMany(mappedBy = "master")
    private List<Member> following;

    @OneToMany(mappedBy = "member")
    private List<Category> categories;
}
