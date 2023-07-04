package com.example.bo.member.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.bo.member.dto.MemberDto;
import com.example.bo.member.repository.MemoryMemberRepository;

public class MemberServiceTest {
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    MemberDto member;
    private MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    private MemberService memberService = new MemberServiceImpl(passwordEncoder, memberRepository);
    
    @BeforeEach
    void beforeEach() {
        String username = "testuser";
        String password = "password1234!";
        String email = "testmail@naver.com";
        String nickname = "nickname";

        member = MemberDto.makeMemberDto(username, password, nickname, email);
    }
    @AfterEach
    void afterEach() {
        memberRepository.clear();
    }
    @Test
    @DisplayName("member signUp")
    void signUpTest() {
        //given
        MemberDto memberDto = member;

        //when
        memberService.signUp(memberDto);

        //then
        MemberDto savedMemberDto = memberService.getByUsername(memberDto.getUsername());
        assertThat(memberDto.getEmail()).isEqualTo(savedMemberDto.getEmail());
    }
    @Test
    @DisplayName("update member's email")
    void updateTest() {
        //given
        MemberDto memberDto = member;
        memberService.signUp(memberDto);
        MemberDto savedMemberDto = memberService.getByUsername(memberDto.getUsername());

        String email = "modifiedEmail@gmail.com";

        //when
        memberService.modifyEmail(savedMemberDto, email);

        //then
        MemberDto modifiedMemberDto = memberService.getByUsername(memberDto.getUsername());
        assertThat(modifiedMemberDto.getMemId()).isEqualTo(savedMemberDto.getMemId());
        assertThat(modifiedMemberDto.getEmail()).isEqualTo(email);
    }
    @Test
    @DisplayName("change password")
    void changePassword() {
        //given
        MemberDto memberDto = member;
        memberService.signUp(memberDto);
        MemberDto savedMemberDto = memberService.getByUsername(memberDto.getUsername());

        String newPassword = "modifypassword123!";
        String oldPassword = "password1234!";

        //when
        memberService.changePassword(savedMemberDto, oldPassword, newPassword);

        //then
        MemberDto modifiedMemberDto = memberService.getByUsername(memberDto.getUsername());
        assertThat(modifiedMemberDto.getMemId()).isEqualTo(savedMemberDto.getMemId());
        assertThat(passwordEncoder.matches(newPassword, modifiedMemberDto.getPassword())).isTrue();
    }
    @Test
    @DisplayName("delete member")
    void deleteMember() {
        //given
        MemberDto memberDto = member;
        memberService.signUp(memberDto);

        List<MemberDto> allMembers = memberService.getAllMember();
        assertThat(allMembers.size()).isEqualTo(1);

        //when
        memberDto = memberService.getByUsername(memberDto.getUsername());
        memberService.deleteMember(memberDto);
        
        //then
        allMembers = memberService.getAllMember();
        assertThat(allMembers.size()).isEqualTo(0);
    }
}
