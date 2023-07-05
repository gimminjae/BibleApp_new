package com.example.bo.member.dto;


import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class MemberDto {
    private String memId;
    private LocalDateTime createDateTime;
    private LocalDateTime updateDateTime;
    @NotNull
    @Size(min = 5, max = 10, message = "아이디는 5 ~ 10자 이어야 합니다.")
    @Pattern(regexp = "^[a-zA-Z0-0]*$", message = "아이디는 영어랑 숫자만 가능합니다.")
    private String username;
    @NotNull
    @Pattern (regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}", message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8 ~20자의 비밀번호여야 합니다.")
    private String password;
    @NotNull
    @Size(min = 5, max = 10, message = "닉네임은 5 ~ 10자 이어야 합니다.")
    private String nickname;
    @NotNull
    @Email
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
