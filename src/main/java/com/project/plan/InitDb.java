package com.project.plan;

import com.project.plan.domain.Category;
import com.project.plan.domain.Plan;
import com.project.plan.domain.User;
import com.project.plan.service.category.CategoryService;
import com.project.plan.service.plan.PlanService;
import com.project.plan.service.user.UserService;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;

@Slf4j
@Component
@RequiredArgsConstructor
public class InitDb {
    private final InitService initService;
    @PostConstruct
    public void init() throws ParseException {
        initService.dbInit1();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final EntityManager em;
        private final UserService userService;
        private final PlanService planService;
        private final CategoryService categoryService;


        public void dbInit1() throws ParseException {
            User user = createUser("test1", "채영", "1234");
            userService.join(user);

            User user2 = createUser("test2", "지연", "1234");
            userService.join(user2);

            Category category1 = Category.createCategory("할일", "#faebeb", user);
            categoryService.save(category1);

            Category category2 = Category.createCategory("공부", "#e1effe", user);
            categoryService.save(category2);

            Category category3 = Category.createCategory("알바", "#e2e3fc", user);
            categoryService.save(category3);

            Category category4 = Category.createCategory("약속", "#fdf1d8", user);
            categoryService.save(category4);


            Category category5 = Category.createCategory("할일", "#faebeb", user2);
            categoryService.save(category5);

            Category category6 = Category.createCategory("공부", "#e1effe", user2);
            categoryService.save(category6);

            Category category7 = Category.createCategory("약속", "#fdf1d8", user2);
            categoryService.save(category7);


            Plan plan1 = Plan.createPlan(user, category1, "2024-05-23", "2024-05-23", "방 청소하기");
            planService.save(plan1);

            Plan plan2 = Plan.createPlan(user, category2, "2024-05-23", "2024-05-23", "단어 암기");
            planService.save(plan2);

            Plan plan3 = Plan.createPlan(user, category3, "2024-05-26", "2024-05-26", "과외");
            planService.save(plan3);

            Plan plan4 = Plan.createPlan(user2, category4, "2024-05-28", "2024-05-31", "제주도 여행");
            planService.save(plan4);

            Plan plan5 = Plan.createPlan(user2, category5, "2024-05-19", "2024-05-19", "식물 물주기");
            planService.save(plan5);

            Plan plan6 = Plan.createPlan(user2, category6, "2024-05-23", "2024-05-23", "알고리즘 2문제 풀기");
            planService.save(plan6);

            Plan plan7 = Plan.createPlan(user2, category7, "2024-05-10", "2024-05-10", "친구");
            planService.save(plan7);

        }

        public User createUser(String username, String nickname, String password) {
            User user = new User();
            user.setUsername(username);
            user.setNickname(nickname);
            user.setPassword(password);

            return user;
        }
    }
}
