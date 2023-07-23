package com.example.bo.plan.repository;

import com.example.bo.plan.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanRepositoryImpl extends PlanRepository, JpaRepository<Plan, Long> {
}
