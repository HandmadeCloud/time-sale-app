package com.example.timesaleapp.controller;

import com.example.timesaleapp.controller.member.UserSignUpDto;
import com.example.timesaleapp.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
//
//    @GetMapping
//    public Long signUp(@RequestBody UserSignUpDto signUpDto){
//        return memberService.join(signUpDto);
//    }


}
