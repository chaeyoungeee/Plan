package com.project.plan.domain;

public enum PlanStatus {
    COMPLETED, INCOMPLETE;

    public PlanStatus toggle() {
        return this == COMPLETED ? INCOMPLETE : COMPLETED;
    }
}
