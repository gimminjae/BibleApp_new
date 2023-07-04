package com.example.bo.member.service;

import org.springframework.stereotype.Service;

import com.example.bo.member.dto.MemberDto;
import com.example.bo.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    @Override
    public void signUp(MemberDto memberDto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'signUp'");
    }

    @Override
    public MemberDto getByUsername(String username) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getByUsername'");
    }

    @Override
    public void modifyEmail(String memId, String email) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'modifyEmail'");
    }
    
}
