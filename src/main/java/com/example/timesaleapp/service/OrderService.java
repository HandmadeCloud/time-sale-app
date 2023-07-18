package com.example.timesaleapp.service;

import com.example.timesaleapp.config.MyAppNotFoundException;
import com.example.timesaleapp.domain.order.dto.OrderProductCreateDto;
import com.example.timesaleapp.domain.member.Member;
import com.example.timesaleapp.domain.order.Order;
import com.example.timesaleapp.domain.order.OrderProduct;
import com.example.timesaleapp.domain.order.dto.OrderDto;
import com.example.timesaleapp.domain.product.Product;
import com.example.timesaleapp.repository.MemberRepository;
import com.example.timesaleapp.repository.OrderRepository;
import com.example.timesaleapp.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ProductRepository productRepository;

    @Transactional
    public Long createOrder(Long memberId, List<OrderProductCreateDto> orderProductCreateDtos) {
        Member member = memberRepository.findById(memberId).orElseThrow(MyAppNotFoundException::new);

        List<OrderProduct> orderProducts = orderProductCreateDtos.stream()
                .map(orderProductCreateDto -> {
                    Product product = productRepository.findById(orderProductCreateDto.productId()).orElseThrow(MyAppNotFoundException::new);
                    return OrderProduct.createOrderProduct(product, product.getPrice(), orderProductCreateDto.count());
                })
                .collect(Collectors.toList());

        Order order = Order.createOrder(member, orderProducts);
        orderRepository.save(order);
        return order.getId();
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public OrderDto cancel(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(MyAppNotFoundException::new);
        order.cancel();
        return null;
    }

//    public void correct(Long id, OrderProductUpdateDto orderProductUpdateDto){
//        Order order = orderRepository.findById(id).orElseThrow(MyAppNotFoundException::new);
//        List<OrderProduct> orderProducts = order.getOrderProducts();
//        orderProducts.correct
//
//    }
}
