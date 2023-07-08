package com.example.bo.base.mail.dto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class MailTo {
    private String address;
    private String title;
    private String message;

    public static MailTo sendEmailAuthCode(String authCode, String email) {
        return MailTo.builder()
                .title("인증코드입니다.")
                .message("인증코드는 %s 입니다.".formatted(authCode))
                .address(email)
                .build();
    }
}
