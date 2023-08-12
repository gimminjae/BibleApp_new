package com.example.bo.member.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@JsonIncludeProperties({"nickname", "role", "createDateTime", "memId", "email"}) //"memId", "username"
public class AuthUser extends User {
    private final String memId;
    private final String nickname;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    private final LocalDateTime createDateTime;
    private String role;
    private String email;

    public AuthUser(Member member) {
        super(member.getEmail(), member.getPassword(), member.getAuthorities());

        memId = member.getMemId();
        nickname = member.getNickname();
        role = member.getRole().toString();
        createDateTime = member.getCreateDateTime();
        email = member.getEmail();
//        this.role = member.getAuthorities().get(0).toString();
    }

    @Override
    public Set<GrantedAuthority> getAuthorities() {
        return super.getAuthorities().stream().collect(Collectors.toSet());
    }
    public AuthUser(Member member, List<GrantedAuthority> authorities) {
        super(member.getMemId(), member.getPassword(), authorities);
        this.memId = member.getMemId();
        this.nickname = member.getNickname();
        this.role = authorities.get(0).toString();
        this.createDateTime = member.getCreateDateTime();
        this.email = member.getEmail();
    }
}
