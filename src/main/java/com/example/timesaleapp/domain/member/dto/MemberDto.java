package com.example.timesaleapp.domain.member.dto;

import com.example.timesaleapp.domain.member.Member;
import com.example.timesaleapp.domain.member.MemberStatus;
import com.example.timesaleapp.domain.order.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder @Slf4j @Getter
public class MemberDto {

    private Long id;
    private String email;
    private String password;
    private String nickName;
    private List<Order> orders;
    private MemberStatus memberStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static MemberDto of(Member member){
        return MemberDto.builder()
                .id(member.getId())
                .email(member.getEmail())
                .password(member.getPassword())
                .nickName(member.getNickName())
                .orders(member.getOrders())
                .memberStatus(member.getMemberStatus())
                .createdAt(member.getCreatedAt())
                .updatedAt(LocalDateTime.now())
                .build();
    }

}
