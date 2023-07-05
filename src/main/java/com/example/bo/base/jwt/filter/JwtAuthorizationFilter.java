package com.example.bo.base.jwt.filter;



import com.example.bo.base.jwt.provider.JwtProvider;
import com.example.bo.member.entity.AuthUser;
import com.example.bo.member.entity.Member;
import com.example.bo.member.service.MemberService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {
    private final JwtProvider jwtProvider;
    private final MemberService memberService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String accessToken = request.getHeader("Authentication");
        String refreshToken = request.getHeader("Authentication-refresh");

        if(accessToken == null || accessToken.isEmpty()) {
            if(refreshToken != null && !refreshToken.isEmpty()) {
//                accessToken = memberService.regenAccessToken(refreshToken);
            }
        }
        //refreshtoken이 없거나 유효하지 않으면 예외발생


        if (accessToken != null && !accessToken.isEmpty()) {
            // 1차 체크(정보가 변조되지 않았는지 체크)
            if (jwtProvider.verify(accessToken)) {
                Map<String, Object> claims = jwtProvider.getClaims(accessToken);
                String username = (String) claims.get("username");
                Member member = memberService.getByUsername(username).toEntity();
                forceAuthentication(member);
            }
        }
        filterChain.doFilter(request, response); //다음 필터를 실행시켜 주어야 한다
    }

    private void forceAuthentication(Member member) {
        AuthUser authUser = new AuthUser(member);

        UsernamePasswordAuthenticationToken authentication =
                UsernamePasswordAuthenticationToken.authenticated(
                        authUser,
                        null,
                        member.getAuthorities()
                );

        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(authentication);
        SecurityContextHolder.setContext(context);
    }
}
