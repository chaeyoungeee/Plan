package com.project.plan.service.plan;

import com.project.plan.domain.Category;
import com.project.plan.domain.Plan;
import com.project.plan.domain.PlanStatus;
import com.project.plan.repository.plan.PlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Primary
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService {
    private final PlanRepository planRepositoryImpl;


    //플랜 저장
    @Transactional
    public Long save(Plan plan) {
        planRepositoryImpl.save(plan);
        return plan.getId();
    }

    //플랜 삭제
    @Transactional
    public void delete(Long id) {
        planRepositoryImpl.delete(id);
    }

    //플랜 수정(날짜, 내용, 날짜)
    @Transactional
    public void update(Long id, String start, String end, String title, Category category) {
        Plan plan = planRepositoryImpl.findById(id);
        plan.setStart(start);
        plan.setEnd(end);
        plan.setTitle(title);
        plan.setCategory(category);
    }


    //플랜 상태 수정
    @Transactional
    public void updateStatus(Long id, PlanStatus status) {
        Plan plan = planRepositoryImpl.findById(id);
        plan.setStatus(status);
    }

    public Plan findById(Long id) {
        return planRepositoryImpl.findById(id);
    }
}
