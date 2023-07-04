package com.example.bo.member.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bo.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, String> {

    Optional<Member> findByUsername(String username);
    
}
