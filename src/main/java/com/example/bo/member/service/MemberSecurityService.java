package com.example.bo.member.service;


import com.example.bo.base.util.ObjectUtil;
import com.example.bo.member.entity.AuthUser;
import com.example.bo.member.entity.Member;
import com.example.bo.member.entity.Role;
import com.example.bo.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberSecurityService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = ObjectUtil.isNullExceptionElseReturnObJect(memberRepository.findByEmail(email));
        List<GrantedAuthority> authorityList = new ArrayList<>();
        if("ADMIN".equals(member.getRole().getRole())) {
            authorityList.add(new SimpleGrantedAuthority(Role.ADMIN.getRole()));
        } else  {
            authorityList.add(new SimpleGrantedAuthority(Role.MEMBER.getRole()));
        }
        return new AuthUser(member, authorityList);
    }
}
