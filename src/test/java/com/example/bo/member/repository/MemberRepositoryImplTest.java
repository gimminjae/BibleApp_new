package com.example.bo.member.repository;


import com.example.bo.base.util.ObjectUtil;
import com.example.bo.member.dto.MemberDto;
import com.example.bo.member.entity.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Sql("/sql/user-repository-test-data.sql")
@TestPropertySource("classpath:test-application.yml")
class MemberRepositoryImplTest {
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private MemberRepository memberRepository;

    String memId = "20230705-b5aa2484b8b345d586804fef824bbd8e";
    String username = "testuser";
    String email = "testmail@naver.com";
    String nickname = "nickname";
    @Test
    @DisplayName("findByUsername")
    void findByUsername() {
        //given

        //when
        Optional<Member> member = memberRepository.findByUsername(username);

        //then
        assertThat(member.isPresent()).isTrue();
        assertThat(member.get().getMemId()).isEqualTo(memId);
    }
    @Test
    @DisplayName("findByNickname")
    void findByNickname() {
        //given

        //when
        Optional<Member> member = memberRepository.findByNickname(nickname);

        //then
        assertThat(member.isPresent()).isTrue();
        assertThat(member.get().getMemId()).isEqualTo(memId);
    }
    @Test
    @DisplayName("findByEmail")
    void findByEmail() {
        //given

        //when
        Optional<Member> member = memberRepository.findByEmail(email);

        //then
        assertThat(member.isPresent()).isTrue();
        assertThat(member.get().getMemId()).isEqualTo(memId);
    }
    @Test
    @DisplayName("findById")
    void findById() {
        //given

        //when
        Optional<Member> member = memberRepository.findById(memId);

        //then
        assertThat(member.isPresent()).isTrue();
        assertThat(member.get().getUsername()).isEqualTo(username);
    }
    @Test
    @DisplayName("modify Member's email")
    void modifyMemberEmailTest() {
        //given
        String modifiedEmail = "modifiedEmail@naver.com";
        String modifiedPassword = "modifiedPassword123!";
        String modifiedNickname = "modifiedNickname";
        Member member = ObjectUtil.isNullExceptionElseReturnObJect(memberRepository.findByUsername(username));

        //when
        member.modifyEmail(modifiedEmail);
        member.changePassword(passwordEncoder.encode(modifiedPassword));
        member.modifyNickname(modifiedNickname);
        memberRepository.save(member);

        //then
        member = ObjectUtil.isNullExceptionElseReturnObJect(memberRepository.findByUsername(username));
        assertThat(member.getEmail()).isEqualTo(modifiedEmail);
        assertThat(passwordEncoder.matches(modifiedPassword, member.getPassword())).isTrue();
        assertThat(member.getNickname()).isEqualTo(modifiedNickname);
    }
    @Test
    @DisplayName("getAllMember and signup")
    void getAllMemberAndSignup() {
        //given
        String username = "testuser2";
        String email = "testmail2@naver.com";
        String nickname = "nickname2";
        String password = "newPassword234!";

        List<Member> allMembers = memberRepository.findAll();
        assertThat(allMembers.size()).isEqualTo(1);

        //when
        memberRepository.save(Member.from(MemberDto.makeMemberDto(username, password, nickname, email)));

        //then
        allMembers = memberRepository.findAll();
        assertThat(allMembers.size()).isEqualTo(2);
    }
    @Test
    @DisplayName("deleteMember By memId")
    void deleteMemberByMemId() {
        //given

        List<Member> allMembers = memberRepository.findAll();
        assertThat(allMembers.size()).isEqualTo(1);

        //when
        memberRepository.deleteById(allMembers.get(0).getMemId());

        //then
        allMembers = memberRepository.findAll();
        assertThat(allMembers.isEmpty()).isTrue();
    }

}