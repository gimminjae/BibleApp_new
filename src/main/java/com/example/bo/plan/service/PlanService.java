package com.example.bo.plan.service;

import com.example.bo.plan.dto.PlanDto;

import java.util.List;

public interface PlanService {
    void create(PlanDto planDto);

    List<PlanDto> getByMemId(String memId);

    PlanDto getByPlanIdAndConfirmByMemId(long planId, String memId);
    PlanDto getByPlanId(long planId);

    void deletePlanAfterConfirmMember(long planId, String memId);

    void updateVerseStatusAfterConfirmMember(long planId, String memId, PlanDto planDto);
}
