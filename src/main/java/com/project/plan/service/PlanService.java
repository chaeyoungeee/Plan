package com.project.plan.service;

import com.project.plan.domain.Category;
import com.project.plan.domain.Plan;
import com.project.plan.domain.PlanStatus;
import com.project.plan.repository.PlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PlanService {
    private final PlanRepository planRepository;

    @Transactional
    //플랜 저장
    public void savePlan(Plan plan) {
        planRepository.save(plan);
    }

    //플랜 삭제
    public void deletePlan(Long id) {
        planRepository.delete(id);
    }

    //플랜 수정(날짜, 내용, 날짜)
    public void update(Long id, Date date, String content, Category category) {
        Plan plan = planRepository.findById(id);
        plan.setPlanDate(date);
        plan.setContent(content);
        plan.setCategory(category);
    }

    //플랜 상태 수정
    public void updateStatus(Long id, PlanStatus status) {
        Plan plan = planRepository.findById(id);
        plan.setStatus(status);
    }

}
