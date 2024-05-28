package com.project.plan.domain;

import com.project.plan.dto.category.CategoryDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Plan> plans = new ArrayList<>();

    private String name;
    private String color;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public void setUser(User user) {
        this.user = user;
        if (!user.getCategories().contains(this)) {
            user.getCategories().add(this);
        }
    }

    public static Category createCategory(String name, String color, User user) {
        Category newCategory = new Category();
        newCategory.setName(name);
        newCategory.setColor(color);
        newCategory.setUser(user);

        return newCategory;
    }

    public CategoryDto toDto() {
        return new CategoryDto(this);
    }

    public static class FriendInfo {
    }
}
