package com.example.bo.member.service;

import java.util.List;

import com.example.bo.member.dto.MemberDto;

public interface MemberService {

    void signUp(MemberDto memberDto);

    MemberDto getByUsername(String username);

    void modifyEmail(MemberDto memberDto, String email);

    void changePassword(MemberDto savedMemberDto, String oldPassword, String newPassword);

    List<MemberDto> getAllMember();

    void deleteMember(MemberDto memberDto);
    
}
