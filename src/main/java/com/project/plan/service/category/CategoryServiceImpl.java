package com.project.plan.service.category;

import com.project.plan.domain.Category;
import com.project.plan.domain.Plan;
import com.project.plan.repository.category.CategoryRepository;
import com.project.plan.service.plan.PlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Primary
@RequiredArgsConstructor
@Transactional
public class CategoryServiceImpl implements CategoryService  {
    private final CategoryRepository categoryRepository;
    private final PlanService planService;


    //카테고리 저장
    @Transactional
    public void save(Category category) {
        categoryRepository.save(category);
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Optional<Category> findById(Long id) {
        return Optional.ofNullable(categoryRepository.findById(id));
    }

    //카테고리 삭제
    @Transactional
    public void delete(Long id) {
        Optional<Category> categoryOptional = findById(id);
        if (categoryOptional.isPresent()) {
            Category category = categoryOptional.get();
            List<Plan> plans = category.getPlans();
            for (Plan plan : plans) {
                planService.delete(plan.getId());
            }
        }

        categoryRepository.delete(id);
    }

    //카테고리 수정(이름, 색상)
    @Transactional
    public void update(Long id, String name, String color) {
        Category category = categoryRepository.findById(id);
        category.setName(name);
        category.setColor(color);
    }
}
