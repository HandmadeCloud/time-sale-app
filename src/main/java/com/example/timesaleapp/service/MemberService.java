package com.example.timesaleapp.service;

import com.example.timesaleapp.config.MyAppNotFoundException;
import com.example.timesaleapp.domain.member.dto.MemberSignUpDto;
import com.example.timesaleapp.domain.member.dto.MemberUpdateDto;
import com.example.timesaleapp.domain.member.Member;
import com.example.timesaleapp.domain.member.dto.MemberDto;
import com.example.timesaleapp.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service @Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    public Long join(MemberSignUpDto signUpDto) {
        Member member = Member.of(signUpDto);
        return memberRepository.save(member).getId();
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    @Transactional
    public MemberDto correct(Long id, MemberUpdateDto memberUpdateDto) {
        Member member = memberRepository.findById(id).orElseThrow(MyAppNotFoundException::new);
        member.update(memberUpdateDto);
        return MemberDto.of(member);
    }

    public MemberDto delete(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(MyAppNotFoundException::new);
        member.changeStatusDeleted();
        return MemberDto.of(member);
    }
}
