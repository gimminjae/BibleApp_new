package com.example.bo.member.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import com.example.bo.base.jwt.provider.JwtProvider;
import com.example.bo.member.entity.Role;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.bo.member.dto.MemberDto;
import com.example.bo.member.repository.MemoryMemberRepository;

public class MemberServiceTest {
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    MemberDto member;
    private MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    private MemberService memberService = new MemberServiceImpl(null, null, null, passwordEncoder, memberRepository, null);
    
    @BeforeEach
    void beforeEach() {
        String password = "password1234!";
        String password2 = "password1234!";
        String email = "testmail@naver.com";
        String nickname = "nickname";

        member = MemberDto.makeMemberDto(email, nickname, password, password2);
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
        MemberDto savedMemberDto = memberService.getByEmail(memberDto.getEmail());
        assertThat(memberDto.getEmail()).isEqualTo(savedMemberDto.getEmail());
    }
    @Test
    @DisplayName("change password")
    void changePassword() {
        //given
        MemberDto savedMemberDto = memberService.getByEmail(member.getEmail());

        String newPassword1 = "modifypassword123!";
        String newPassword2 = "modifypassword123!";
        String oldPassword = "password1234!";

        //when
        memberService.changePassword(savedMemberDto.getMemId(), oldPassword, newPassword1, newPassword2);

        //then
        MemberDto modifiedMemberDto = memberService.getByEmail(member.getEmail());
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
        member = memberService.getByEmail(member.getEmail());
        memberService.deleteMember(member);
        
        //then
        allMembers = memberService.getAllMember();
        assertThat(allMembers.size()).isEqualTo(0);
    }
    @Test
    @DisplayName("modify nickname")
    void modifyNickname() {
        //given
        MemberDto savedMemberDto = memberService.getByEmail(member.getEmail());

        String nickname = "modifiedNickname";

        //when
        memberService.modifyNickname(savedMemberDto, nickname);

        //then
        MemberDto modifiedMemberDto = memberService.getByEmail(member.getEmail());
        assertThat(modifiedMemberDto.getMemId()).isEqualTo(savedMemberDto.getMemId());
        assertThat(modifiedMemberDto.getNickname()).isEqualTo(nickname);
    }
    @Test
    @DisplayName("change role DEPTSUBADMIN")
    void changeRoleDEPTSUBADMIN() {
        //given
        MemberDto savedMemberDto = memberService.getByEmail(member.getEmail());

        //when
        memberService.empowerDEPTSUBADMIN(savedMemberDto);

        //then
        MemberDto modifiedMemberDto = memberService.getByEmail(member.getEmail());
        assertThat(modifiedMemberDto.getMemId()).isEqualTo(savedMemberDto.getMemId());
        assertThat(modifiedMemberDto.getRole().toString()).isEqualTo(Role.DEPTSUBADMIN.toString());
    }
    @Test
    @DisplayName("confirm email duplication")
    void confirmEmailDuplication() {
        //given
        String email = "testmail@naver.com";

        //when & then
        Assertions.assertThrows(DataIntegrityViolationException.class, () -> memberService.confirmEmail(email));
    }
    @Test
    @DisplayName("confirm nickname duplication")
    void confirmNicknameDuplication() {
        //given
        String nickname = "nickname";

        //when & then
        Assertions.assertThrows(DataIntegrityViolationException.class, () -> memberService.confirmNicknameDuplication(nickname));
    }
}
