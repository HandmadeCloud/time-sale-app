package com.example.timesaleapp.domain.member;

import com.example.timesaleapp.domain.BaseTimeEntity;
//import com.example.timesaleapp.domain.order.Order;
import com.example.timesaleapp.controller.member.MemberUpdateDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @Entity
@Builder @Table(name="members")
@AllArgsConstructor
@NoArgsConstructor
public class Member extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    private String nickName;

//    @Column
//    private Order order;

    @Enumerated(value = EnumType.STRING)
    private MemberStatus memberStatus;

    public void changeStatusDeleted(){
        this.memberStatus = MemberStatus.DELETED;
    }

    public void update(MemberUpdateDto memberUpdateDto) {
        this.email = memberUpdateDto.email();
        this.password = memberUpdateDto.password();
        this.nickName = memberUpdateDto.nickName();
    }
}
