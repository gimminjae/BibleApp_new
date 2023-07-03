package com.example.bo.member.service;

import org.springframework.stereotype.Service;

import com.example.bo.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    
}
