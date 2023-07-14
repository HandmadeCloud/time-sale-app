package com.example.timesaleapp.service;

import com.example.timesaleapp.config.MyAppNotFoundException;
import com.example.timesaleapp.controller.member.MemberSignUpDto;
import com.example.timesaleapp.controller.member.MemberUpdateDto;
import com.example.timesaleapp.domain.member.Member;
import com.example.timesaleapp.domain.member.MemberDto;
import com.example.timesaleapp.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public void join(MemberSignUpDto signUpDto) {
        Member member = signUpDto.toEntity();
        memberRepository.save(member);
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

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
