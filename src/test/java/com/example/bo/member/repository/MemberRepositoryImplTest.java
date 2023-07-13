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
    String email = "testmail@naver.com";
    String nickname = "nickname";
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
        assertThat(member.get().getEmail()).isEqualTo(email);
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
        memberRepository.save(Member.from(MemberDto.makeMemberDto(email, nickname, password)));

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