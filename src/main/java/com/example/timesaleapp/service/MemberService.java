package com.example.timesaleapp.service;

import com.example.timesaleapp.controller.member.UserSignUpDto;
import com.example.timesaleapp.domain.member.Member;
import com.example.timesaleapp.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Long join(UserSignUpDto signUpDto) {
        Member member = signUpDto.toEntity();
        return memberRepository.save(member).getId();
    }
}
