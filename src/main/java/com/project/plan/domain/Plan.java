package com.project.plan.domain;

import com.project.plan.dto.plan.PlanDto;
import jakarta.persistence.*;
import lombok.*;

import java.text.ParseException;

@Entity
@Table
@Data
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "plan_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    private String title;
    @Column(name = "start_day")
    private String start;
    @Column(name = "end_day")
    private String end;

    @Enumerated(EnumType.STRING)
    private PlanStatus status;

    private Long friendPlanId;


    public static Plan createPlan(User user, Category category, String start, String end, String title) throws ParseException {
        Plan newPlan = new Plan();

        newPlan.setUser(user);
        newPlan.setCategory(category);
        newPlan.setStart(start);
        newPlan.setEnd(end);
        newPlan.setTitle(title);
        newPlan.setStatus(PlanStatus.INCOMPLETE);

        return newPlan;
    }

    public void setUser(User user) {
        this.user = user;
        if (user == null) {
            return;
        }
        user.getPlans().add(this);
    }

    public void setCategory(Category category) {
        this.category = category;
        category.getPlans().add(this);
    }

    public PlanDto toDto() {
        return new PlanDto(this);
    }


    @Override
    public String toString() {
        return "Plan{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", start='" + start + '\'' +
                ", end='" + end + '\'' +
                ", status=" + status +
                '}';
    }

}
