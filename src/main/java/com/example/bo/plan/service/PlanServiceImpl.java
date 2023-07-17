package com.example.bo.plan.service;

import com.example.bo.plan.dto.PlanDto;
import com.example.bo.plan.entity.Plan;
import com.example.bo.plan.repository.PlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService {
    private final PlanRepository planRepository;

    @Override
    public void create(PlanDto planDto) {
        planRepository.save(Plan.from(planDto));
    }
}
