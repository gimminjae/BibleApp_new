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
    private final static String START_DATE_MUST_BEFORE_END_DATE_MSG = "시작일이 종료일보다 앞서야합니다.";
    private final PlanRepository planRepository;

    @Override
    public void create(PlanDto planDto) {
        Plan plan = Plan.from(planDto);
        if (!plan.getStartDate().isBefore(plan.getEndDate())) {
            throw new IllegalArgumentException(START_DATE_MUST_BEFORE_END_DATE_MSG);
        }
        planRepository.save(plan);
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
        planRepository.delete(returnMemberAfterConfirm(planId, memId));
    }

    @Override
    public void updateVerseStatusAfterConfirmMember(long planId, String memId, PlanDto planDto) {
        Plan plan = returnMemberAfterConfirm(planId, memId);
        plan.updateVerseStatus(planDto.getGoalStatus());
        planRepository.save(plan);
    }

    @Override
    public void updatePlanInfoAfterConfirm(PlanDto planDto, String memId) {
        Plan plan = returnMemberAfterConfirm(planDto.getPlanId(), memId);
        plan.updatePlanInfo(planDto);
        planRepository.save(plan);
    }

    private Plan returnMemberAfterConfirm(long planId, String memId) {
        Plan plan = ObjectUtil.isNullExceptionElseReturnObJect(planRepository.findById(planId), NO_PLAN_MSG);
        if (!plan.getMember().getMemId().equals(memId)) {
            throw new AccessDeniedException(NO_ACCESS_AUTH_MSG);
        }
        return plan;
    }
}
