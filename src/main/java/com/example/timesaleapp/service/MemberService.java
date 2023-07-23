package com.example.timesaleapp.service;

import com.example.timesaleapp.annotation.Validation;
import com.example.timesaleapp.config.MyAppNotFoundException;
import com.example.timesaleapp.config.ResponseException;
import com.example.timesaleapp.constant.ResponseTemplateStatus;
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
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    @Validation
    public Long createMember(MemberSignUpDto signUpDto) {
        validateDuplicateEmail(signUpDto.email());
        Member member = Member.of(signUpDto);

        return memberRepository.save(member).getMemberId();
    }

    public List<MemberDto> getMembers() {
        List<Member> members = memberRepository.findAll();

        return members.stream().map(MemberDto::of).collect(Collectors.toList());
    }

    @Transactional
    @Validation
    public MemberDto updateMember(Long id, MemberUpdateDto memberUpdateDto) {
        Member member = memberRepository.findById(id).orElseThrow(MyAppNotFoundException::new);
        member.update(memberUpdateDto);

        return MemberDto.of(member);
    }

    @Transactional
    public MemberDto deleteMember(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(MyAppNotFoundException::new);
        member.changeStatusDeleted();

        return MemberDto.of(member);
    }


    private void validateDuplicateEmail(String email) throws ResponseException {
        if (memberRepository.findByEmail(email).isPresent()){
            throw new ResponseException(ResponseTemplateStatus.EMAIL_DUPLICATE);
        }
    }

}
