package com.example.bo.member.service;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.bo.base.util.ObjectUtil;
import com.example.bo.member.dto.MemberDto;
import com.example.bo.member.entity.Member;
import com.example.bo.member.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;

    @Override
    public void signUp(MemberDto memberDto) {
        memberDto.setPassword(passwordEncoder.encode(memberDto.getPassword()));
        Member member = Member.from(memberDto);
        try {
            memberRepository.save(member);
        } catch(DataIntegrityViolationException e) {
            throw new IllegalArgumentException("중복된 정보입니다.");
        }
    }

    @Override
    public MemberDto getByUsername(String username) {
        return ObjectUtil.isNullExceptionElseReturnObJect(memberRepository.findByUsername(username)).toDto();
    }

    @Override
    public void modifyEmail(MemberDto memberDto, String email) {
        Member member = ObjectUtil.isNullExceptionElseReturnObJect(memberRepository.findById(memberDto.getMemId()));
        member.modifyEmail(email);
        memberRepository.save(member);
    }

    @Override
    public void changePassword(MemberDto memberDto, String oldPassword, String newPassword) {
        Member member = ObjectUtil.isNullExceptionElseReturnObJect(memberRepository.findById(memberDto.getMemId()));
        if(passwordEncoder.matches(oldPassword, memberDto.getPassword())) {
            member.changePassword(passwordEncoder.encode(newPassword));
        } else {
            throw new AccessDeniedException("권한이 없습니다.");
        }
        memberRepository.save(member);
    }

    @Override
    public List<MemberDto> getAllMember() {
        return memberRepository.findAll().stream().map(Member::toDto).toList();
    }

    @Override
    public void deleteMember(MemberDto memberDto) {
        memberRepository.deleteById(memberDto.getMemId());
    }

    @Override
    public void modifyNickname(MemberDto savedMemberDto, String email) {

    }

    @Override
    public void empowerDEPTSUBADMIN(MemberDto savedMemberDto) {

    }

}
