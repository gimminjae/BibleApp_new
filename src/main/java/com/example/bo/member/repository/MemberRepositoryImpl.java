package com.example.bo.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bo.member.entity.Member;

@Repository
public interface MemberRepositoryImpl extends JpaRepository<Member, String>, MemberRepository {
    
}
