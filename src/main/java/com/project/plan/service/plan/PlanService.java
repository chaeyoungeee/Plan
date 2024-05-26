package com.project.plan.service.plan;

import com.project.plan.domain.Category;
import com.project.plan.domain.User;
import com.project.plan.domain.Plan;
import com.project.plan.domain.PlanStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PlanService {
    Long save(Plan plan);
    void delete(Long id);
    void update(Long id, String start, String end, String title, Category category);
    void updateStatus(Long id, PlanStatus status);
    Plan findById(Long id);
    List<Plan> findAll();
}
