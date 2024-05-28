package com.project.plan.service.plan;

import com.project.plan.domain.Category;
import com.project.plan.domain.Plan;
import com.project.plan.domain.PlanStatus;
import com.project.plan.domain.User;
import com.project.plan.dto.plan.AddPlanRequest;
import com.project.plan.dto.plan.PlanDto;
import com.project.plan.repository.category.CategoryRepository;
import com.project.plan.repository.plan.PlanRepository;
import com.project.plan.repository.user.UserRepository;
import com.project.plan.service.category.CategoryService;
import com.project.plan.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Primary
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService {
    private final PlanRepository planRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;


    //플랜 저장
    @Transactional
    public PlanDto save(AddPlanRequest plan) throws ParseException {
        User user = userRepository.findById(plan.getUserId());
        Category category = categoryRepository.findById(plan.getCategoryId());

        Plan planEntity = Plan.createPlan(user, category, plan.getStart(), plan.getEnd(), plan.getTitle());

        planRepository.save(planEntity);

        return planEntity.toDto();
    }

    @Override
    public Long save(Plan plan) {
        planRepository.save(plan);
        return plan.getId();
    }

    //플랜 삭제
    @Transactional
    public void delete(Long id) {
        planRepository.delete(id);
    }

    //플랜 수정(날짜, 내용, 날짜)
    @Transactional
    public void update(Long id, String start, String end, String title, Category category) {
        Plan plan = planRepository.findById(id);
        plan.setStart(start);
        plan.setEnd(end);
        plan.setTitle(title);
        plan.setCategory(category);
    }

    @Override
    public List<PlanDto> findByMemberId(Long id) {
        List<Plan> plans = planRepository.findByMemberId(id);
        List<PlanDto> planDtos = plans.stream()
                .map(PlanDto::new)
                .collect(Collectors.toList());;
        return planDtos;
    }


    //플랜 상태 수정
    @Transactional
    public void updateStatus(Long id, PlanStatus status) {
        Plan plan = planRepository.findById(id);
        plan.setStatus(status);
    }

    public Plan findById(Long id) {
        return planRepository.findById(id);
    }

    @Override
    public List<Plan> findAll() {
        return planRepository.findAll();
    }
}
