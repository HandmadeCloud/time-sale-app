package com.example.timesaleapp.service;

import com.example.timesaleapp.domain.member.Member;
import com.example.timesaleapp.domain.order.Order;
import com.example.timesaleapp.domain.order.OrderProduct;
import com.example.timesaleapp.domain.order.OrderStatus;
import com.example.timesaleapp.domain.order.dto.OrderDto;
import com.example.timesaleapp.domain.order.dto.OrderProductCreateDto;
import com.example.timesaleapp.domain.product.Product;
import com.example.timesaleapp.repository.MemberRepository;
import com.example.timesaleapp.repository.OrderRepository;
import com.example.timesaleapp.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;
    @Mock
    private MemberRepository memberRepository;
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private OrderService orderService;

    private Product product1;
    private Product product2;
    private Member member1;
    private Order order1;
    private List<Order> orders = new ArrayList<>();

    @BeforeEach
    public void setUp(){
        // Mock 객체 생성
        product1 = mock(Product.class);
        product2 = mock(Product.class);
        member1 = mock(Member.class);

        OrderProduct orderProduct1 = OrderProduct.builder()
                .product(product1)
                .orderPrice(1000)
                .count(1)
                .build();

        OrderProduct orderProduct2 = OrderProduct.builder()
                .product(product2)
                .orderPrice(2000)
                .count(2)
                .build();

        // Order 객체 생성 및 주문 상품 추가
        order1 = Order.builder()
                .id(1L)
                .status(OrderStatus.ACCEPTED)
                .member(member1)
                .orderProducts(Arrays.asList(orderProduct1, orderProduct2))
                .build();

        orders.add(order1);
    }

    @Test
    @DisplayName("주문을 생성할 수 있다.")
    void createOrder() {
        // given
        OrderProductCreateDto createDto1 = new OrderProductCreateDto(1L, 2);
        OrderProductCreateDto createDto2 = new OrderProductCreateDto(2L, 4);
        List<OrderProductCreateDto> createDtos = Arrays.asList(createDto1, createDto2);
        given(memberRepository.findById(anyLong())).willReturn(Optional.ofNullable(member1));
        given(productRepository.findById(anyLong())).willReturn(Optional.ofNullable(product1));
        given(orderRepository.save(any(Order.class))).willReturn(order1);
        // when
        Long orderId = orderService.createOrder(1L, createDtos);
        // then
        Assertions.assertThat(orderId).isEqualTo(1L);
    }

    @Test
    @DisplayName("주문 정보를 전체 조회할 수 있다.")
    void getAllOrders() {
        // given
        given(orderRepository.findAll()).willReturn(orders);
        // when
        List<Order> allOrders = orderService.getAllOrders();
        // then
        Assertions.assertThat(allOrders).isEqualTo(orders);
    }

//    @Test
//    @DisplayName("주문 정보를 취소할 수 있다.")
//    void cancel() {
//        // given
//        given(orderRepository.findById(anyLong())).willReturn(Optional.ofNullable(order1));
//        // when
//        OrderDto canceledDto = orderService.cancel(1L);
//        // then
//        Assertions.assertThat()
//    }
}