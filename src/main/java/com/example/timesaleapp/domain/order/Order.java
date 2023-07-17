package com.example.timesaleapp.domain.order;

import com.example.timesaleapp.domain.BaseTimeEntity;
import com.example.timesaleapp.domain.member.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.FetchType.*;

@Getter @Entity @Builder
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
public class Order extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderProduct> orderProducts = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public static void createOrder(Member member, OrderProduct... orderProducts) {
    // 수정
    }
}
