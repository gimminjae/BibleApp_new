package com.example.bo.member.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import com.example.bo.base.jwt.provider.JwtProvider;
import com.example.bo.member.entity.Role;
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
    private MemberService memberService = new MemberServiceImpl(null, null, passwordEncoder, memberRepository);
    
    @BeforeEach
    void beforeEach() {
        String username = "testuser";
        String password = "password1234!";
        String email = "testmail@naver.com";
        String nickname = "nickname";

        member = MemberDto.makeMemberDto(username, password, nickname, email);
        memberService.signUp(member);
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

        //then
        MemberDto savedMemberDto = memberService.getByUsername(memberDto.getUsername());
        assertThat(memberDto.getEmail()).isEqualTo(savedMemberDto.getEmail());
    }
    @Test
    @DisplayName("update member's email")
    void updateTest() {
        //given
        MemberDto savedMemberDto = memberService.getByUsername(member.getUsername());

        String email = "modifiedEmail@gmail.com";

        //when
        memberService.modifyEmail(savedMemberDto, email);

        //then
        MemberDto modifiedMemberDto = memberService.getByUsername(member.getUsername());
        assertThat(modifiedMemberDto.getMemId()).isEqualTo(savedMemberDto.getMemId());
        assertThat(modifiedMemberDto.getEmail()).isEqualTo(email);
    }
    @Test
    @DisplayName("change password")
    void changePassword() {
        //given
        MemberDto savedMemberDto = memberService.getByUsername(member.getUsername());

        String newPassword1 = "modifypassword123!";
        String newPassword2 = "modifypassword123!";
        String oldPassword = "password1234!";

        //when
        memberService.changePassword(savedMemberDto, oldPassword, newPassword1, newPassword2);

        //then
        MemberDto modifiedMemberDto = memberService.getByUsername(member.getUsername());
        assertThat(modifiedMemberDto.getMemId()).isEqualTo(savedMemberDto.getMemId());
        assertThat(passwordEncoder.matches(newPassword1, modifiedMemberDto.getPassword())).isTrue();
    }
    @Test
    @DisplayName("delete member")
    void deleteMember() {
        //given
        List<MemberDto> allMembers = memberService.getAllMember();
        assertThat(allMembers.size()).isEqualTo(1);

        //when
        member = memberService.getByUsername(member.getUsername());
        memberService.deleteMember(member);
        
        //then
        allMembers = memberService.getAllMember();
        assertThat(allMembers.size()).isEqualTo(0);
    }
    @Test
    @DisplayName("modify nickname")
    void modifyNickname() {
        //given
        MemberDto savedMemberDto = memberService.getByUsername(member.getUsername());

        String nickname = "modifiedNickname";

        //when
        memberService.modifyNickname(savedMemberDto, nickname);

        //then
        MemberDto modifiedMemberDto = memberService.getByUsername(member.getUsername());
        assertThat(modifiedMemberDto.getMemId()).isEqualTo(savedMemberDto.getMemId());
        assertThat(modifiedMemberDto.getNickname()).isEqualTo(nickname);
    }
    @Test
    @DisplayName("change role DEPTSUBADMIN")
    void changeRoleDEPTSUBADMIN() {
        //given
        MemberDto savedMemberDto = memberService.getByUsername(member.getUsername());

        //when
        memberService.empowerDEPTSUBADMIN(savedMemberDto);

        //then
        MemberDto modifiedMemberDto = memberService.getByUsername(member.getUsername());
        assertThat(modifiedMemberDto.getMemId()).isEqualTo(savedMemberDto.getMemId());
        assertThat(modifiedMemberDto.getRole().toString()).isEqualTo(Role.DEPTSUBADMIN.toString());
    }
}
