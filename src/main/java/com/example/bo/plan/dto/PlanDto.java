package com.example.bo.plan.dto;

import com.example.bo.member.dto.MemberDto;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlanDto {
    private long planId;
    private LocalDateTime createDateTime;
    private LocalDateTime updateDateTime;
    private String startDate;
    private String endDate;
    private String planName;
    private int goalPercent;
    private int oldGoalCount;
    private int newGoalCount;
    private List<Bible> goalStatus;
    private MemberDto member;
}
