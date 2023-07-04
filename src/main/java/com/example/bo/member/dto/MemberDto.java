package com.example.bo.member.dto;

import javax.management.relation.Role;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MemberDto {
    private String memId;
    private String username;
    private String password;
    private String nickname;
    private String email;
    private String role;

    public static MemberDto makeMemberDto(String username, String password, String nickname, String email) {
        return MemberDto.builder()
                .username(username)
                .password(password)
                .nickname(nickname)
                .email(email)
                .build();
    }
}
