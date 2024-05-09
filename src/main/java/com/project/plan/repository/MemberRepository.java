package com.project.plan.repository;

import com.project.plan.domain.Member;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {
    private final EntityManager em;

    //저장
    public void save(Member member) {
        em.persist(member);
    }

    //아이디로 멤버 찾기
    public Member findById(Long id) {
        return em.find(Member.class, id);
    }

    //유저네임으로 멤버찾기
    public Member findByUsername(String username) {
        return em.createQuery("select m from Member m where m.username = :username", Member.class)
                .setParameter("username", username)
                .getSingleResult();
    }


    //닉네임으로 멤버찾기
    public Member findByNickname(String nickname) {
        return em.createQuery("select m from Member m where m.nickname = :nickname", Member.class)
                .setParameter("nickname", nickname)
                .getSingleResult();
    }

    //모든 멤버 찾기
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
