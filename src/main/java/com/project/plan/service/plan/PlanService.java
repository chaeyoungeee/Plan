package com.project.plan.service.plan;

import com.project.plan.domain.Category;
import com.project.plan.domain.User;
import com.project.plan.domain.Plan;
import com.project.plan.domain.PlanStatus;
import com.project.plan.dto.plan.AddPlanRequest;
import com.project.plan.dto.plan.PlanDto;
import com.project.plan.dto.plan.UpdatePlanRequest;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;

@Service
public interface PlanService {
    PlanDto save(AddPlanRequest plan) throws ParseException;
    Long save(Plan plan);
    void delete(Long id);
    void update(Long id, UpdatePlanRequest request);

    void toggle(Long id);
    List<PlanDto> findByMemberId(Long id);
    Plan findById(Long id);
    List<Plan> findAll();
}
