package com.project.plan.repository.category;

import com.project.plan.domain.Category;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Primary
@RequiredArgsConstructor
public class CategoryRepositoryImpl implements CategoryRepository {
    private final EntityManager em;

    //카테고리 저장
    public void save(Category category) {
        em.persist(category);
    }

    //아이디로 카테고리 찾기
    public Category findById(Long id) {
        return em.find(Category.class, id);
    }

    //모든 카테고리 찾기
    public List<Category> findAll() {
        return em.createQuery("select DISTINCT c from Category c join fetch c.plans", Category.class)
                .getResultList();
    }

    //카테고리 삭제
    public void delete(Long id) {
        em.createQuery("delete from Category c where c.id=:id")
                .setParameter("id", id)
                .executeUpdate();;
    }
}
