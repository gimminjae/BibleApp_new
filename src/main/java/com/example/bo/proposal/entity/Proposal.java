package com.example.bo.proposal.entity;

import com.example.bo.member.entity.Member;
import com.example.bo.proposal.dto.ProposalDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Proposal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long proposalId;
    private LocalDateTime createDateTime;
    private String title;
    private String content;
    @ManyToOne
    @JoinColumn(name = "mem_id")
    private Member member;

    public static Proposal from(ProposalDto proposalDto) {
        return Proposal.builder()
                .title(proposalDto.getTitle())
                .content(proposalDto.getContent())
                .createDateTime(LocalDateTime.now())
                .member(new Member(proposalDto.getMemId()))
                .build();
    }
    public ProposalDto toDto() {
        return ProposalDto.builder()
                .proposalId(this.getProposalId())
                .title(this.getTitle())
                .content(this.getContent())
                .createDateTime(this.getCreateDateTime())
                .nickname(this.getMember().getNickname())
                .email(this.getMember().getEmail())
                .memId(this.getMember().getMemId())
                .build();
    }
}
