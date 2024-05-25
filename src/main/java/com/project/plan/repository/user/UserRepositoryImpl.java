package com.project.plan.repository.user;

import com.project.plan.domain.User;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Primary
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {
    private final EntityManager em;

    //저장
    public void save(User user) {
        em.persist(user);
    }

    //아이디로 멤버 찾기
    public User findById(Long id) {
        return em.find(User.class, id);
    }

    //유저네임으로 멤버찾기
    public Optional<User> findByUsername(String username) {
        return em.createQuery("select m from User m where m.username = :username", User.class)
                .setParameter("username", username)
                .getResultList()
                .stream().findAny();
    }


    //닉네임으로 멤버찾기
    public Optional<User> findByNickname(String nickname) {
        return em.createQuery("select m from User m where m.nickname = :nickname", User.class)
                .setParameter("nickname", nickname)
                .getResultList()
                .stream().findAny();
    }

    //모든 멤버 찾기
    public List<User> findAll() {
        return em.createQuery("select m from User m", User.class)
                .getResultList();
    }
}
