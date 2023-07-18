package com.example.timesaleapp.domain.order;

import com.example.timesaleapp.config.MyAppNotFoundException;
import com.example.timesaleapp.domain.order.dto.OrderDto;
import com.example.timesaleapp.domain.order.dto.OrderProductUpdateDto;
import com.example.timesaleapp.domain.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Builder @Table(name = "order_products")
@Entity @Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderProduct {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    private int orderPrice;

    private int count;

    public static OrderProduct createOrderProduct(Product product, int price, int count) {
        OrderProduct orderProduct = OrderProduct.builder()
                .product(product)
                .orderPrice(price * count)
                .count(count)
                .build();
        product.deductStock(count);

        return orderProduct;
    }

}
