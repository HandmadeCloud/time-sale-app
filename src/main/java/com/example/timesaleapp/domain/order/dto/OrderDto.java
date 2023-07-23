package com.example.timesaleapp.domain.order.dto;

import com.example.timesaleapp.domain.member.Member;
import com.example.timesaleapp.domain.order.Order;
import com.example.timesaleapp.domain.order.OrderProduct;
import com.example.timesaleapp.domain.order.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder @Getter
public class OrderDto {

    private Long orderId;
    private Member member;
    private List<OrderProduct> orderProducts;
    private int totalPrice;
    private int totalQuantity;
    private OrderStatus orderStatus;

    public static OrderDto of(Order order){

        return OrderDto.builder()
                .orderId(order.getOrderId())
                .member(order.getMember())
                .orderProducts(order.getOrderProducts())
                .orderStatus(order.getOrderStatus())
                .totalPrice(order.getTotalPrice())
                .totalQuantity(order.getTotalQuantity())
                .build();
    }

}
