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
@RequestMapping("/api/v1/members") //검증 끝
public class MemberController {

    private final MemberService memberService;

    @GetMapping("")
    public ResponseTemplate<List<Member>> getAll(){
        return ResponseTemplate.valueOf(memberService.getAllMembers());
    }

    @PostMapping("/signup")
    public ResponseTemplate<Long> signUp(@RequestBody MemberSignUpDto signUpDto){
        return ResponseTemplate.valueOf(memberService.join(signUpDto));
    }

    @PatchMapping("/update/{id}")
    public ResponseTemplate<MemberDto> correct(@PathVariable Long id, @RequestBody MemberUpdateDto memberUpdateDto){
        MemberDto memberDto = memberService.correct(id, memberUpdateDto);
        return ResponseTemplate.valueOf(memberDto);
    }

    @PatchMapping("/delete/{id}")
    public ResponseTemplate<MemberDto> delete(@PathVariable Long id){
        return ResponseTemplate.valueOf(memberService.delete(id));
    }
}
