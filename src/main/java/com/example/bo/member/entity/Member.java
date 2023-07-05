package com.example.bo.member.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import com.example.bo.member.dto.MemberDto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "member")
public class Member {
    @Id
    private String memId;

    private LocalDateTime createDateTime;

    private LocalDateTime updateDateTime;

    @Column(unique = true)
    private String username;

    private String password;

    @Column(unique = true)
    private String nickname;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Column(unique = true)
    private String email;

    public static Member from(MemberDto memberDto) {
        return Member.builder()
                .memId("%s-%s".formatted(LocalDate.now().toString().replace("-", ""), UUID.randomUUID().toString().replace("-", "")))
                .createDateTime(LocalDateTime.now())
                .updateDateTime(LocalDateTime.now())
                .username(memberDto.getUsername())
                .password(memberDto.getPassword())
                .nickname(memberDto.getNickname())
                .role(memberDto.getRole() != null && memberDto.getRole().equals("ADMIN") ? Role.ADMIN : Role.MEMBER)
                .email(memberDto.getEmail())
                .build();
    }

    public MemberDto toDto() {
        return MemberDto.builder()
                .memId(this.getMemId())
                .createDateTime(this.getCreateDateTime())
                .updateDateTime(this.getUpdateDateTime())
                .username(this.getUsername())
                .password(this.getPassword())
                .email(this.getEmail())
                .nickname(getNickname())
                .role(this.getRole().toString())
                .build();
    }

    public void setUpdateDateTime(LocalDateTime updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    public void modifyEmail(String email) {
        setEmail(email);
        setUpdateDateTime(LocalDateTime.now());
    }

    private void setEmail(String email) {
        this.email = email;
    }

    public void changePassword(String newPassword) {
        setUpdateDateTime(LocalDateTime.now());
        setPassword(newPassword);
    }

    private void setPassword(String newPassword) {
        this.password = newPassword;
    }

}