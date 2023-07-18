package com.example.bo.plan.entity;

import com.example.bo.member.entity.Member;
import com.example.bo.plan.converter.BibleGoalConverter;
import com.example.bo.plan.dto.Bible;
import com.example.bo.plan.dto.PlanDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "plan")
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long planId;
    private LocalDateTime createDateTime;
    private LocalDateTime updateDateTime;
    private LocalDate startDate;
    private LocalDate endDate;
    private String planName;
    private int goalPercent;
    private int oldGoalCount;
    private int newGoalCount;
    @Convert(converter = BibleGoalConverter.class)
    private List<Bible> goalStatus;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="memId")
    private Member member;
    public static Plan from(PlanDto planDto) {
        List<Bible> bibleList = Bible.createAllList();
        return Plan.builder()
                .createDateTime(LocalDateTime.now())
                .startDate(LocalDate.parse(planDto.getStartDate().split("T")[0], DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .endDate(LocalDate.parse(planDto.getEndDate().split("T")[0], DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                .planName(planDto.getPlanName())
                .oldGoalCount(planDto.getOldGoalCount())
                .newGoalCount(planDto.getNewGoalCount())
                .goalStatus(planDto.getGoalStatus())
                .member(planDto.getMember().toEntity())
                .goalStatus(bibleList)
                .build();
    }
    public static PlanDto toDto(Plan plan) {
        return PlanDto.builder()
                .planId(plan.getPlanId())
                .planName(plan.getPlanName())
                .oldGoalCount(plan.getOldGoalCount())
                .newGoalCount(plan.getNewGoalCount())
                .goalPercent(plan.getGoalPercent())
                .goalStatus(plan.getGoalStatus())
                .createDateTime(plan.getCreateDateTime())
                .updateDateTime(plan.getUpdateDateTime())
                .startDate(plan.getStartDate().toString())
                .endDate(plan.getEndDate().toString())
                .build();
    }
}
