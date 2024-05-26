package com.project.plan.service;

import com.project.plan.domain.Category;
import com.project.plan.domain.User;
import com.project.plan.domain.Plan;
import com.project.plan.repository.plan.PlanRepositoryImpl;
import com.project.plan.service.category.CategoryServiceImpl;
import com.project.plan.service.plan.PlanServiceImpl;
import com.project.plan.service.user.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PlanServiceImplTest {
    @Autowired
    UserServiceImpl memberService;

    @Autowired
    CategoryServiceImpl categoryService;

    @Autowired
    PlanRepositoryImpl planRepository;

    @Autowired
    PlanServiceImpl planService;

    @Test

    @Rollback(value = false)
    public void save() throws Exception {
        //given
        User member = new User();
        member.setNickname("lim");

        memberService.join(member);

        Category category = new Category();
        category.setName("할일");

        categoryService.save(category);

        Plan plan = new Plan();
        plan.setStart("2024-05-13");
        plan.setEnd("2024-05-13");
        plan.setCategory(category);
        plan.setTitle("물 마시기");
        plan.setUser(member);



        //when
//        Long planId = planService.save(plan);
//
//        //then
//        Assertions.assertEquals(plan, planRepository.findById(planId));
//
//        log.info(String.valueOf(member.getPlans().size()) + "abcd");
    }


    @Test
    @Rollback(value = false)
    public void update() throws Exception {
        //given
        User member = new User();
        member.setNickname("lim");

        memberService.join(member);

        Category category = new Category();
        category.setName("할일");

        categoryService.save(category);

        Plan plan = new Plan();
        plan.setStart("2024.05.13");
        plan.setEnd("2024.05.13");
        plan.setCategory(category);
        plan.setTitle("물 마시기");
        plan.setUser(member);



        //when
        Category category2 = new Category();
        category2.setName("중요");
        categoryService.save(category2);

        planService.save(plan);
        plan.setCategory(category2);
        plan.setTitle("공부");

        Plan p = planService.findById(plan.getId());

        //then
        Assertions.assertEquals(p.getTitle(), "공부");
    }

}