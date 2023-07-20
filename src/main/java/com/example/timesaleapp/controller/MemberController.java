package com.example.timesaleapp.controller;

import com.example.timesaleapp.config.ResponseTemplate;
import com.example.timesaleapp.domain.member.Member;
import com.example.timesaleapp.domain.member.dto.MemberDto;
import com.example.timesaleapp.domain.member.dto.MemberSignUpDto;
import com.example.timesaleapp.domain.member.dto.MemberUpdateDto;
import com.example.timesaleapp.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("")
    public ResponseTemplate<List<MemberDto>> getMembers(){

        return ResponseTemplate.valueOf(memberService.getMembers());
    }

    @PostMapping("/signup")
    public ResponseTemplate<Long> signUp(@RequestBody MemberSignUpDto signUpDto){

        return ResponseTemplate.valueOf(memberService.createMember(signUpDto));
    }

    @PatchMapping("/update/{memberId}")
    public ResponseTemplate<MemberDto> updateMember(@PathVariable Long memberId, @RequestBody MemberUpdateDto memberUpdateDto){

        return ResponseTemplate.valueOf(memberService.updateMember(memberId, memberUpdateDto));
    }

    @PatchMapping("/delete/{memberId}")
    public ResponseTemplate<MemberDto> deleteMember(@PathVariable Long memberId){

        return ResponseTemplate.valueOf(memberService.deleteMember(memberId));
    }
}
