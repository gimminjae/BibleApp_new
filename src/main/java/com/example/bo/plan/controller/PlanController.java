package com.example.bo.plan.controller;

import com.example.bo.base.exception.ValidationUtil;
import com.example.bo.plan.dto.PlanDto;
import com.example.bo.plan.service.PlanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/plan")
public class PlanController {
    private final PlanService planService;
    @PostMapping("")
    public ResponseEntity<Void> createBiblePlan(@RequestBody @Valid PlanDto planDto, BindingResult bindingResult) {
        ValidationUtil.returnValidError(bindingResult);
        planService.create(planDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
