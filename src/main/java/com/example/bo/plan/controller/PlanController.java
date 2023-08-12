package com.example.bo.plan.controller;

import com.example.bo.base.exception.ValidationUtil;
import com.example.bo.member.entity.AuthUser;
import com.example.bo.member.service.MemberService;
import com.example.bo.plan.dto.PlanDto;
import com.example.bo.plan.service.PlanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/plan")
public class PlanController {
    private final PlanService planService;
    private final MemberService memberService;
    @PostMapping("")
    public ResponseEntity<Void> createBiblePlan(@AuthenticationPrincipal AuthUser authUser, @RequestBody @Valid PlanDto planDto, BindingResult bindingResult) {
        ValidationUtil.returnValidError(bindingResult);
        planDto.setMember(memberService.getByMemId(authUser.getMemId()));
        planService.create(planDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("")
    public ResponseEntity<List<PlanDto>> getMyBiblePlan(@AuthenticationPrincipal AuthUser authUser) {
        return new ResponseEntity<>(planService.getByMemId(authUser.getMemId()), HttpStatus.OK);
    }
    @GetMapping("/{planId}")
    public ResponseEntity<PlanDto> getPlanById(@AuthenticationPrincipal AuthUser authUser, @PathVariable long planId) {
        return new ResponseEntity<>(planService.getByPlanIdAndConfirmByMemId(planId, authUser.getMemId()), HttpStatus.OK);
    }
    @DeleteMapping("/{planId}")
    public ResponseEntity<Void> deletePlan(@AuthenticationPrincipal AuthUser authUser, @PathVariable long planId) {
        planService.deletePlanAfterConfirmMember(planId, authUser.getMemId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PatchMapping("/{planId}")
    public ResponseEntity<Void> updatePlan(@AuthenticationPrincipal AuthUser authUser,
                                           @PathVariable long planId,
                                           @RequestBody PlanDto planDto) {
        planService.updateVerseStatusAfterConfirmMember(planId, authUser.getMemId(), planDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("")
    public ResponseEntity<Void> updatePlan(@AuthenticationPrincipal AuthUser authUser,
                                           @RequestBody PlanDto planDto
                                           ) {
        planService.updatePlanInfoAfterConfirm(planDto, authUser.getMemId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
