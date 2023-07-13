package com.example.bo.member.repository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bo.member.entity.Member;

public interface MemberRepository {

    Optional<Member> findByEmail(String email);

    Member save(Member member);

    Optional<Member> findById(String memId);

    List<Member> findAll();

    void deleteById(String memId);

    Optional<Member> findByNickname(String nickname);
}
