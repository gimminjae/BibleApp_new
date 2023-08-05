package com.example.bo.plan.entity;

import com.example.bo.base.util.ObjectUtil;
import com.example.bo.bibleverse.enums.BibleChapterInfo;
import com.example.bo.member.entity.Member;
import com.example.bo.plan.converter.BibleGoalConverter;
import com.example.bo.plan.dto.Bible;
import com.example.bo.plan.dto.PlanDto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Entity
@Getter
@Setter
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
    private float goalPercent;
    private int oldGoalCount;
    private int newGoalCount;
    @Convert(converter = BibleGoalConverter.class)
    private List<Bible> goalStatus;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="mem_id")
    private Member member;
    private int totalReadCount;
    private int currentReadCount;
    private float readCountPerDay;
    private int restDay;
    public static Plan from(PlanDto planDto) {
        List<Bible> bibleList = Bible.createAllList();
        LocalDate startDate = LocalDate.parse(planDto.getStartDate().split("T")[0], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate endDate = LocalDate.parse(planDto.getEndDate().split("T")[0], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        int totalReadCount = BibleChapterInfo.OLD_BIBLE_COUNT * planDto.getOldGoalCount() + BibleChapterInfo.NEW_BIBLE_COUNT * planDto.getNewGoalCount();
        return Plan.builder()
                .createDateTime(LocalDateTime.now())
                .startDate(startDate)
                .endDate(endDate)
                .planName(planDto.getPlanName())
                .oldGoalCount(planDto.getOldGoalCount())
                .newGoalCount(planDto.getNewGoalCount())
                .goalStatus(planDto.getGoalStatus())
                .member(planDto.getMember().toEntity())
                .goalStatus(bibleList)
                .totalReadCount(totalReadCount)
                .currentReadCount(0)
                .build();
    }
    public PlanDto toDto() {
        return PlanDto.builder()
                .planId(this.getPlanId())
                .planName(this.getPlanName())
                .oldGoalCount(this.getOldGoalCount())
                .newGoalCount(this.getNewGoalCount())
                .goalPercent(this.getGoalPercent())
                .goalStatus(this.getGoalStatus())
                .createDateTime(this.getCreateDateTime())
                .updateDateTime(this.getUpdateDateTime())
                .startDate(this.getStartDate().toString())
                .endDate(this.getEndDate().toString())
                .totalReadCount(this.getTotalReadCount())
                .currentReadCount(this.getCurrentReadCount())
                .restDay((int) ChronoUnit.DAYS.between(LocalDate.now(), this.getEndDate()))
                .readCountPerDay((float) (this.getTotalReadCount() - this.getCurrentReadCount()) / (float) ChronoUnit.DAYS.between(LocalDate.now(), this.getEndDate()))
                .build();
    }

    public void updateVerseStatus(List<Bible> goalStatus) {
        this.setGoalStatus(goalStatus);
        this.operateGoalStatus(goalStatus);
    }

    private void operateGoalStatus(List<Bible> goalStatus) {
        float sum = 0;
        for (Bible bible : goalStatus) {
            int goalCount;
            if (bible.isTestament()) goalCount = this.getOldGoalCount();
            else goalCount = this.getNewGoalCount();
            for (int readCount : bible.getVerseStatus()) {
                sum += Math.min(readCount, goalCount);
            }
        }
        float totalVerseCount = (float) (BibleChapterInfo.OLD_BIBLE_COUNT * this.getOldGoalCount() + BibleChapterInfo.NEW_BIBLE_COUNT * this.getNewGoalCount());
        this.setCurrentReadCount((int) sum);
        this.setGoalPercent(Float.parseFloat(ObjectUtil.divide(sum, totalVerseCount)) * Float.parseFloat("100"));
        this.setUpdateDateTime(LocalDateTime.now());
    }

    public void setUpdateDateTime(LocalDateTime updateDateTime) {
        this.updateDateTime = updateDateTime;
    }
    public void setGoalPercent(float goalPercent) {
        this.goalPercent = goalPercent;
    }
    public void setGoalStatus(List<Bible> goalStatus) {
        this.goalStatus = goalStatus;
    }

    public void updatePlanInfo(PlanDto planDto) {
        this.setPlanName(planDto.getPlanName());
        this.setStartDate(LocalDate.parse(planDto.getStartDate().split("T")[0], DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        this.setEndDate(LocalDate.parse(planDto.getEndDate().split("T")[0], DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        this.setOldGoalCount(planDto.getOldGoalCount());
        this.setNewGoalCount(planDto.getNewGoalCount());
        this.setUpdateDateTime(planDto.getUpdateDateTime());
        this.operateGoalStatus(this.getGoalStatus());
        this.setTotalReadCount((BibleChapterInfo.OLD_BIBLE_COUNT * planDto.getOldGoalCount() + BibleChapterInfo.NEW_BIBLE_COUNT * planDto.getNewGoalCount()));
    }
}
