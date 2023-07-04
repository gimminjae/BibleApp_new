package com.example.bo.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bo.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, String> {
    
}
