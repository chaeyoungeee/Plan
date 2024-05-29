package com.project.plan.service.plan;

import com.project.plan.domain.Category;
import com.project.plan.domain.Plan;
import com.project.plan.domain.PlanStatus;
import com.project.plan.domain.User;
import com.project.plan.dto.plan.AddPlanRequest;
import com.project.plan.dto.plan.AddPlanWithFriendRequest;
import com.project.plan.dto.plan.PlanDto;
import com.project.plan.dto.plan.UpdatePlanRequest;
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

import static org.hibernate.query.sqm.tree.SqmNode.log;

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

    @Transactional
    @Override
    public void update(Long id, UpdatePlanRequest request) {
        Plan plan = planRepository.findById(id);
        plan.setTitle(request.getTitle());
        plan.setStart(request.getStart());
        plan.setEnd(request.getEnd());
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

    @Transactional
    @Override
    public void toggle(Long id) {
        Plan plan = planRepository.findById(id);
        plan.setStatus(plan.getStatus().toggle());
        log.info(plan.getStatus().toString());
    }

    @Override
    public List<PlanDto> findByMemberId(Long id) {
        List<Plan> plans = planRepository.findByMemberId(id);
        List<PlanDto> planDtos = plans.stream()
                .map(PlanDto::new)
                .collect(Collectors.toList());;
        return planDtos;
    }



    public Plan findById(Long id) {
        return planRepository.findById(id);
    }

    @Override
    public List<Plan> findAll() {
        return planRepository.findAll();
    }

    @Transactional
    @Override
    public void saveWithFriend(AddPlanWithFriendRequest request) throws ParseException {
        User user = userRepository.findById(request.getUserId());
        User friend = userRepository.findById(request.getFriendId());

        Category category = categoryRepository.findById(8L);

        Plan planEntity1 = Plan.createPlan(user, category, request.getStart(), request.getEnd(), request.getTitle());

        Plan planEntity2 = Plan.createPlan(friend, category, request.getStart(), request.getEnd(), request.getTitle());

        planRepository.save(planEntity1);
        planRepository.save(planEntity2);

        planEntity1.setFriendPlanId(planEntity2.getId());
        planEntity2.setFriendPlanId(planEntity1.getId());
    }

    @Transactional
    @Override
    public void deleteWithFriend(Long id) {
        Plan plan = planRepository.findById(id);
        Long friendPlanId = plan.getFriendPlanId();
        planRepository.delete(id);
        planRepository.delete(friendPlanId);
    }
}
