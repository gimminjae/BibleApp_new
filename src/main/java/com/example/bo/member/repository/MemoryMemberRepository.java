package com.example.bo.member.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;

import com.example.bo.base.util.ObjectUtil;
import com.example.bo.member.entity.Member;

public class MemoryMemberRepository implements MemberRepository {
    private List<Member> memberList = new ArrayList<>();

    @Override
    public List<Member> findAll() {
        return memberList;
    }

    @Override
    public Member save(Member entity) {
        Member member = null;
        try {
            member = ObjectUtil.isNullExceptionElseReturnObJect(memberList.stream().filter(data -> data.getMemId().equals(entity.getMemId())).findFirst());
        } catch(Exception e) {

        }
        if(member != null) {
            memberList.remove(member);
        }
        memberList.add(entity);
        return entity;
    }

    @Override
    public Optional<Member> findById(String id) {
        return memberList.stream().filter(member -> member.getMemId().equals(id)).findFirst();
    }

    @Override
    public void deleteById(String id) {
        Member deletedMember = null;
        for(Member member : memberList) {
            if(member.getMemId().equals(id)) deletedMember = member;
        }
        if(deletedMember != null) {
            memberList.remove(deletedMember);
        }
    }

    public void clear() {
        memberList.clear();
    }

    @Override
    public Optional<Member> findByUsername(String username) {
        return memberList.stream().filter(member -> member.getUsername().equals(username)).findFirst();
    }

    @Override
    public Optional<Member> findByEmail(String email) {
        return memberList.stream().filter(member -> member.getEmail().equals(email)).findFirst();
    }
}
