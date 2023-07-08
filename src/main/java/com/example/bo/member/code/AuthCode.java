package com.example.bo.member.code;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@RedisHash(value = "authCode", timeToLive = 60 * 5) //5분
public class AuthCode {

    @Id
    private String email;
    private String code;
    private LocalDateTime createdDateTime;
    private LocalDateTime expiredDateTime;

    @Indexed
    private String refreshToken;

    public static AuthCode from(String code, String email) {
        return AuthCode.builder()
                .email(email)
                .createdDateTime(LocalDateTime.now())
                .expiredDateTime(LocalDateTime.now().plusMinutes(5))
                .code(code)
                .build();
    }
    public void setExpiredDateTime(LocalDateTime expiredDateTime) {
        this.expiredDateTime = expiredDateTime;
    }
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void update(LocalDateTime expiredDateTime, String sb) {
        this.setRefreshToken(sb);
        this.setExpiredDateTime(expiredDateTime);

    }
}

