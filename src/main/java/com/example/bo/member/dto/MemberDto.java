package com.example.bo.member.dto;


import com.example.bo.member.entity.Member;
import com.example.bo.member.entity.Role;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {
    private String memId;
    private LocalDateTime createDateTime;
    private LocalDateTime updateDateTime;

    @NotNull(message = "비밀번호를 입력하세요.")
    @NotBlank(message = "비밀번호를 입력하세요.")
    @Pattern (regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}", message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8 ~20자의 비밀번호여야 합니다.")
    private String password;

    @NotNull(message = "비밀번호를 확인을 입력하세요.")
    @NotBlank(message = "비밀번호 확인을 입력하세요.")
    @Pattern (regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,20}", message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8 ~20자의 비밀번호여야 합니다.")
    private String password2;

    @NotNull(message = "닉네임을 입력하세요.")
    @NotBlank(message = "닉네임을 입력하세요.")
    @Size(min = 5, max = 10, message = "닉네임은 5 ~ 10자 이어야 합니다.")
    private String nickname;

    @NotNull(message = "이메일을 입력하세요.")
    @NotBlank(message = "이메일을 입력하세요.")
    @Email
    private String email;
    private String role;

    public static MemberDto makeMemberDto(String email, String nickname, String password) {
        return MemberDto.builder()
                .password(password)
                .nickname(nickname)
                .email(email)
                .build();
    }
    public static MemberDto makeMemberDto(String email, String nickname, String password, String password2) {
        return MemberDto.builder()
                .password(password)
                .password2(password2)
                .nickname(nickname)
                .email(email)
                .build();
    }

    public Member toEntity() {
        return Member.builder()
                .memId(this.getMemId())
                .createDateTime(this.getCreateDateTime())
                .updateDateTime(this.getUpdateDateTime())
                .email(this.getEmail())
                .nickname(this.getNickname())
                .password(this.getPassword())
                .role(Role.valueOf(this.getRole()))
                .build();
    }
}
