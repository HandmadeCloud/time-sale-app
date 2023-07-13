package com.example.timesaleapp.repository;

import com.example.timesaleapp.domain.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional
    public void testMember() throws  Exception{
        Member member = new Member( 1L, "joje@naver.com","tiptip123!","hi");
        memberRepository.save(member);
        List<Member> all = memberRepository.findAll();
        Assertions.assertThat(all.get(1)).isEqualTo(member.getId());

    }

}