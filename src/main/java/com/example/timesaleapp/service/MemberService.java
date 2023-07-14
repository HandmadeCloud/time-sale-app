package com.example.timesaleapp.service;

import com.example.timesaleapp.config.MyAppNotFoundException;
import com.example.timesaleapp.controller.member.MemberSignUpDto;
import com.example.timesaleapp.domain.member.Member;
import com.example.timesaleapp.domain.member.MemberDto;
import com.example.timesaleapp.domain.member.Status;
import com.example.timesaleapp.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public MemberDto correct(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(MyAppNotFoundException::new);
        return MemberDto.of(member);
    }

    public MemberDto delete(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(MyAppNotFoundException::new);
        member.changeStatus();
        return MemberDto.of(member);
    }
}
