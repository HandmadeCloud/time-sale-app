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
@RequestMapping("api/v1/members") //검증 끝
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/signup")
    public String getSignUpPage(){
        return "signup";
    }

    @GetMapping("/")
    public ResponseTemplate<List<Member>> getAllMembers(){
        return ResponseTemplate.valueOf(memberService.getAllMembers());
    }

    @PostMapping("/signup")
    public ResponseTemplate<Long> signUp(@RequestBody MemberSignUpDto signUpDto){
        return ResponseTemplate.valueOf(memberService.join(signUpDto));
    }

    @PatchMapping("/{id}/update")
    public ResponseTemplate<MemberDto> correctMember(@PathVariable Long id, @RequestBody MemberUpdateDto memberUpdateDto){
        MemberDto memberDto = memberService.correct(id, memberUpdateDto);
        return ResponseTemplate.valueOf(memberDto);
    }

    @PatchMapping("/{id}/delete")
    public ResponseTemplate<MemberDto> deleteMember(@PathVariable Long id){
        return ResponseTemplate.valueOf(memberService.delete(id));
    }

}
