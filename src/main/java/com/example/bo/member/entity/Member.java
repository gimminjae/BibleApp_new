package com.example.bo.member.entity;

import java.time.LocalDate;
import java.util.UUID;

import com.example.bo.member.dto.MemberDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
@Builder
public class Member {
    @Id
    private String memId;

    @Column(unique = true)
    private String username;

    private String password;

    @Column(unique = true)
    private String nickname;

    private Role role;

    @Column(unique = true)
    private String email;

    public static Member from(MemberDto memberDto) {
        return Member.builder()
        .memId("%s%s".formatted(LocalDate.now().toString(), UUID.randomUUID().toString().replace("-", "")))
        .username(memberDto.getUsername())
        .password(memberDto.getPassword())
        .nickname(memberDto.getNickname())
        .role(memberDto.getRole().equals("ADMIN") ? Role.ADMIN : Role.MEMBER)
        .email(memberDto.getEmail())
        .build();
    }
}
