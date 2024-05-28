package com.project.plan.repository.plan;

import com.project.plan.domain.Plan;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanRepository {
    void save(Plan plan);
    Plan findById(Long id);
    List<Plan> findAll();

    public List<Plan> findByMemberId(Long id);
    void delete(Long id);
}
