package com.example.bo.member.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import com.example.bo.member.dto.MemberDto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

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

    public void modifyNickname(String nickname) {
        setNickname(nickname);
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void empowerDEPTSUBADMIN() {
        setRole(Role.DEPTSUBADMIN);
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if(this.getRole().toString().equals("ADMIN")) {
            authorities.add(new SimpleGrantedAuthority("ADMIN"));
        } else if(this.getRole().toString().equals("DEPTADMIN")) {
            authorities.add(new SimpleGrantedAuthority("DEPTADMIN"));
        } else if (this.getRole().toString().equals("DEPTSUBADMIN")) {
            authorities.add(new SimpleGrantedAuthority("DEPTSUBADMIN"));
        } else {
            authorities.add(new SimpleGrantedAuthority("MEMBER"));
        }

        return authorities;
    }

    public Map<String, Object> getAccessTokenClaims() {
        Map<String, Object> map = new HashMap<>();
        map.put("createDateTime", getCreateDateTime().toString());
        map.put("nickname", getNickname());
        map.put("email", getEmail());

        return map;
    }
}
