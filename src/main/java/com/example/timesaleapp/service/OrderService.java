package com.example.timesaleapp.service;

import com.example.timesaleapp.config.MyAppNotFoundException;
import com.example.timesaleapp.domain.member.Member;
import com.example.timesaleapp.domain.order.Order;
import com.example.timesaleapp.domain.order.OrderProduct;
import com.example.timesaleapp.domain.product.Product;
import com.example.timesaleapp.repository.MemberRepository;
import com.example.timesaleapp.repository.OrderRepository;
import com.example.timesaleapp.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    @Transactional
    public void createOrder(Long memberId, Long productId, int count) {

        Member member = memberRepository.findById(memberId).orElseThrow(MyAppNotFoundException::new);
        Product product = productRepository.findById(productId).orElseThrow(MyAppNotFoundException::new);



        OrderProduct orderProduct = OrderProduct.createOrderProduct(product, product.getPrice(), count);

        Order.createOrder(member, orderProduct);
    }
}
