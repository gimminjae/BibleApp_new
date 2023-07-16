package com.example.bo.plan.entity;

import com.example.bo.mapping.memplan.entity.MemberPlan;
import com.example.bo.plan.converter.BibleGoalConverter;
import com.example.bo.plan.dto.Bible;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "plan")
public class Plan {
    @Id
    private String planId;
    private LocalDateTime createDateTime;
    private LocalDateTime updateDateTime;
    private String planName;
    private int goalPercent;
    private int oldGoal;
    private int newGoal;
    @Convert(converter = BibleGoalConverter.class)
    private List<Bible> goalStatus;
    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL)
    private List<MemberPlan> member;
}
