package com.example.bo.plan.service;

import com.example.bo.member.entity.Member;
import com.example.bo.plan.dto.PlanDto;
import com.example.bo.plan.entity.Plan;
import com.example.bo.plan.repository.PlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService {
    private final PlanRepository planRepository;

    @Override
    public void create(PlanDto planDto) {
        planRepository.save(Plan.from(planDto));
    }

    @Override
    public List<PlanDto> getByMemId(String memId) {
        return planRepository.findByMember(new Member(memId)).stream().map(Plan::toDto).toList();
//        return planRepository.findByMemId(memId).stream().map(Plan::toDto).toList();
    }
}
