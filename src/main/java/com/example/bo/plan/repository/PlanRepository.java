package com.example.bo.plan.repository;

import com.example.bo.member.entity.Member;
import com.example.bo.plan.dto.PlanDto;
import com.example.bo.plan.entity.Plan;

import java.util.List;

public interface PlanRepository {
    Plan save(Plan plan);

    List<Plan> findByMember(Member member);

//    List<Plan> findByMemId(String memId);
}
