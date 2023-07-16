package com.example.bo.member.refresh.entity;

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
@RedisHash(value = "refresh", timeToLive = 60 * 60 * 24 * 30) //refresh token 기간 -> 1달(30일)
public class RefreshToken {

    @Id
    private String id;
    private LocalDateTime createdDateTime;
    private LocalDateTime expiredDateTime;

    @Indexed
    private String refreshToken;

    public static RefreshToken from(String email, String refreshToken) {
        return RefreshToken.builder()
                .id(email)
                .createdDateTime(LocalDateTime.now())
                .expiredDateTime(LocalDateTime.now().plusMonths(1))
                .refreshToken(refreshToken)
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
