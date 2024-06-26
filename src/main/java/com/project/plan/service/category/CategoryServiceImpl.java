package com.project.plan.service.category;

import com.project.plan.domain.Category;
import com.project.plan.domain.Plan;
import com.project.plan.domain.User;
import com.project.plan.dto.category.AddCategoryRequest;
import com.project.plan.dto.category.CategoryDto;
import com.project.plan.dto.category.UpdateCategoryRequest;
import com.project.plan.repository.category.CategoryRepository;
import com.project.plan.repository.user.UserRepository;
import com.project.plan.service.plan.PlanService;
import com.project.plan.service.user.UserService;
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
    private final UserRepository userRepository;
    private final PlanService planService;

    //카테고리 저장
    @Override
    @Transactional
    public CategoryDto save(Category category) {
        categoryRepository.save(category);
        return category.toDto();
    }

    @Override
    public CategoryDto save(AddCategoryRequest category) {
        User user = userRepository.findById(category.getUserId());
        Category categoryEntity = Category.createCategory(category.getName(), category.getColor(), user);
        categoryRepository.save(categoryEntity);

        return categoryEntity.toDto();
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
        Category category = findById(id).orElseThrow(() -> new NullPointerException("해당 카테고리가 존재하지 않습니다."));

        List<Plan> plans = category.getPlans();
        for (Plan plan : plans) {
            planService.delete(plan.getId());
            categoryRepository.delete(id);
        }
    }

    //카테고리 수정(이름, 색상)
    @Transactional
    public void update(Long id, UpdateCategoryRequest request) {
        Category category = categoryRepository.findById(id);
        category.setName(request.getName());
        category.setColor(request.getColor());
    }
}
