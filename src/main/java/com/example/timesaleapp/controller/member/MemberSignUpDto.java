package com.example.timesaleapp.controller.member;

import com.example.timesaleapp.config.ResponseException;
import com.example.timesaleapp.constant.ResponseTemplateStatus;
import com.example.timesaleapp.domain.Validatable;
import com.example.timesaleapp.domain.member.Member;

import static com.example.timesaleapp.constant.Constant.REGEX_EMAIL;
import static com.example.timesaleapp.constant.Constant.REGEX_PWD;
import static com.example.timesaleapp.domain.member.Status.ACTIVE;

public record MemberSignUpDto(String email, String password, String nickName) implements Validatable {

    public Member toEntity() {
        return Member.builder()
                .email(email)
                .password(password)
                .nickName(nickName)
                .status(ACTIVE)
                .build();
    }

    @Override
    public void validate() throws ResponseException {
        if(!REGEX_EMAIL.matcher(this.email).matches()){
            throw new ResponseException(ResponseTemplateStatus.EMAIL_FORM_INVALID);
        }
        if(!REGEX_PWD.matcher(this.password).matches()){
            throw new ResponseException(ResponseTemplateStatus.PASSWORD_FORM_INVALID);
        }
    }

}
