package com.example.bo.plan.repository;

import com.example.bo.member.entity.Member;
import com.example.bo.plan.dto.PlanDto;
import com.example.bo.plan.entity.Plan;

import java.util.List;
import java.util.Optional;

public interface PlanRepository {
    Plan save(Plan plan);

    List<Plan> findByMember(Member member);

    Optional<Plan> findById(long planId);

//    List<Plan> findByMemId(String memId);
}
