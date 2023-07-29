package com.example.bo.plan.dto;

import com.example.bo.member.dto.MemberDto;
import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime createDateTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime updateDateTime;
    private String startDate;
    private String endDate;
    private String planName;
    private float goalPercent;
    private int oldGoalCount;
    private int newGoalCount;
    private List<Bible> goalStatus;
    private MemberDto member;
}
