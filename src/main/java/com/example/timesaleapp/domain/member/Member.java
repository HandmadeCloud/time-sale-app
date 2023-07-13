package com.example.timesaleapp.domain.member;

import com.example.timesaleapp.domain.BaseTimeEntity;
import com.example.timesaleapp.domain.order.Order;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @Entity
@Builder @Table(name="member")
@AllArgsConstructor
@NoArgsConstructor
public class Member extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String pwd;

    private String nickName;

    private Address address;

//    @Column
//    private Order order;

    @Enumerated(value = EnumType.STRING)
    private Status status;

}
