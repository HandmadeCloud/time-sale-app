package com.example.timesaleapp.domain.member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDto {

    private Long id;
    private String email;
    private String password;
    private String nickName;
    private MemberStatus memberStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static MemberDto of(Member member){
        MemberDto memberDto = MemberDto.builder()
                .id(member.getId())
                .email(member.getEmail())
                .password(member.getPassword())
                .nickName(member.getNickName())
                .memberStatus(member.getMemberStatus())
                .createdAt(member.getCreatedAt())
                .updatedAt(LocalDateTime.now())
                .build();
        return memberDto;
    }

}
