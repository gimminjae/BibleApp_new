package com.example.bo.plan.service;

import com.example.bo.base.util.ObjectUtil;
import com.example.bo.member.entity.Member;
import com.example.bo.plan.dto.PlanDto;
import com.example.bo.plan.entity.Plan;
import com.example.bo.plan.repository.PlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService {
    private final static String NO_PLAN_MSG = "읽기표 정보가 존재하지 않습니다.";
    private final static String NO_ACCESS_AUTH_MSG = "권한이 없습니다.";
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

    @Override
    public PlanDto getByPlanIdAndConfirmByMemId(long planId, String memId) {
        Optional<Plan> optionalPlan = planRepository.findById(planId);
        Plan plan;
        if(optionalPlan.isPresent()) {
            plan = optionalPlan.get();
        } else {
            throw new NullPointerException(NO_PLAN_MSG);
        }
        if(!plan.getMember().getMemId().equals(memId)) {
            throw new AccessDeniedException(NO_ACCESS_AUTH_MSG);
        }
        return plan.toDto();
    }

    @Override
    public PlanDto getByPlanId(long planId) {
        return ObjectUtil.isNullExceptionElseReturnObJect(planRepository.findById(planId), NO_PLAN_MSG).toDto();
    }

    @Override
    public void deletePlanAfterConfirmMember(long planId, String memId) {
        Plan plan = ObjectUtil.isNullExceptionElseReturnObJect(planRepository.findById(planId), NO_PLAN_MSG);
        if (!plan.getMember().getMemId().equals(memId)) {
            throw new AccessDeniedException(NO_ACCESS_AUTH_MSG);
        }
        planRepository.delete(plan);
    }
}
