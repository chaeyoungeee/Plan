package com.project.plan.repository;

import com.project.plan.domain.Plan;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PlanRepository {
    private final EntityManager em;

    //플랜 저장
    public void save(Plan plan) {
        em.persist(plan);
    }

    //아이디로 플랜 찾기
    public Plan findById(Long id) {
        return em.find(Plan.class, id);
    }

    //모든 플랜 찾기
    public List<Plan> findAll() {
        return em.createQuery("select p from Plan p", Plan.class)
                .getResultList();
    }

    //플랜 삭제
    public void delete(Long id) {
        em.createQuery("delete from Plan p where p.id=:id", Plan.class)
                .setParameter("id", id);
    }
}
