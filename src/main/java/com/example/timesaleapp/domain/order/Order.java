package com.example.timesaleapp.domain.order;

import com.example.timesaleapp.domain.BaseTimeEntity;
import com.example.timesaleapp.domain.member.Member;
import com.example.timesaleapp.domain.product.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import java.util.ArrayList;
import java.util.List;

import static com.example.timesaleapp.domain.order.OrderStatus.*;
import static jakarta.persistence.FetchType.*;

@Getter @Entity @Builder @DynamicUpdate
@Table(name = "orders")
@AllArgsConstructor
@NoArgsConstructor
public class Order extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    @JsonIgnore
    private Member member;

    @OneToMany(mappedBy = "order")
    @Builder.Default
    @JsonIgnore
    private List<OrderProduct> orderProducts = new ArrayList<>();

    @Column(name = "total_price")
    private int totalPrice;

    @Column(name = "total_quantity")
    private int totalQuantity;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    public static Order createOrder(Member member, List<OrderProduct> orderProducts) {

        return Order.builder()
                .member(member)
                .orderProducts(orderProducts)
                .orderStatus(ACCEPTED)
                .totalPrice(calculateTotalPrice(orderProducts))
                .totalQuantity(calculateTotalQuantity(orderProducts))
                .build();
    }

    public void cancel(){
        this.orderStatus = CANCELED;

        for (OrderProduct orderProduct : orderProducts) {
            Product product = orderProduct.getProduct();
            int canceledQuantity = orderProduct.getOrderProductCount();
            product.addStock(canceledQuantity);
        }
    }

    private static int calculateTotalPrice(List<OrderProduct> orderProducts){

        return orderProducts.stream()
                .mapToInt(orderProduct -> orderProduct.getOrderProductPrice())
                .sum();
    }

    private static int calculateTotalQuantity(List<OrderProduct> orderProducts){

        return orderProducts.stream()
                .mapToInt(orderProduct -> orderProduct.getOrderProductCount())
                .sum();
    }
}
