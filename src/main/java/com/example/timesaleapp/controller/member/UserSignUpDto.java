package com.example.timesaleapp.controller.member;

import com.example.timesaleapp.domain.Validatable;
import com.example.timesaleapp.domain.member.Address;
import com.example.timesaleapp.domain.member.Member;

import static com.example.timesaleapp.constant.Constant.REGEX_EMAIL;
import static com.example.timesaleapp.constant.Constant.REGEX_PWD;
import static com.example.timesaleapp.domain.member.Status.ACTIVE;

public record UserSignUpDto(String email, String pwd,String nickName, Address address) implements Validatable {

    public Member toEntity() {
        return Member.builder()
                .email(email)
                .pwd(pwd)
                .nickName(nickName)
//                .address(address)
//                .status(ACTIVE)
                .build();
    }

    @Override
    public void validate() {
        if(!REGEX_EMAIL.matcher(this.email).matches()){

        }
        if(!REGEX_PWD.matcher(this.pwd).matches()){

        }
    }

}
