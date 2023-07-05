package com.example.bo.member.repository;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
@Sql("/sql/user-repository-test-data.sql")
@TestPropertySource("classpath:test-application.yml")
class MemberRepositoryImplTest {
    @Autowired
    private MemberRepository memberRepository;
    @Test
    void test1() {
        System.out.println("test");
        System.out.println(memberRepository);
    }
}