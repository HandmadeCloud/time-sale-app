package com.example.timesaleapp.controller.member;

import com.example.timesaleapp.controller.member.MemberSignUpDto;
import com.example.timesaleapp.domain.member.Member;
import com.example.timesaleapp.domain.member.MemberDto;
import com.example.timesaleapp.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/member")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/signup")
    public String getSignUpPage(){
        return "signup";
    }

    @PostMapping("/signup")
    public String signUp(@RequestBody MemberSignUpDto signUpDto){
        memberService.join(signUpDto);
        return "redirect:/";
    }

    @GetMapping("/members")
    public List<Member> getAllMembers(){
        return memberService.getAllMembers();
    }

    @PatchMapping("/{id}/update")
    public String correctMember(@PathVariable Long id){
        MemberDto memberDto = memberService.correct(id);
        return "redirect:/";
    }

    @PatchMapping("/{id}/delete")
    public String deleteMember(@PathVariable Long id){
        MemberDto memberDto = memberService.delete(id);
        return "redirect:/";
    }







}
