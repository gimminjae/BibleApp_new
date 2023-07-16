package com.example.bo.mapping.memplan.entity;

import com.example.bo.member.entity.Member;
import com.example.bo.plan.entity.Plan;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "member_plan")
public class MemberPlan {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mem_id")
    private Member member;
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "plan_id")
    private Plan plan;
}
