package com.example.timesaleapp.controller;

import com.example.timesaleapp.domain.member.Member;
import com.example.timesaleapp.domain.order.Order;
import com.example.timesaleapp.domain.order.OrderProduct;
import com.example.timesaleapp.domain.order.OrderStatus;
import com.example.timesaleapp.domain.order.dto.OrderDto;
import com.example.timesaleapp.domain.order.dto.OrderProductCreateDto;
import com.example.timesaleapp.domain.product.Product;
import com.example.timesaleapp.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.timesaleapp.domain.order.OrderStatus.CANCELED;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(OrderController.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private OrderService orderService;

    private List<Order> orders = new ArrayList<>();

    private Product product1;
    private Product product2;
    private Member member1;
    private OrderProduct orderProduct1;
    private OrderProduct orderProduct2;
    private Order order1;
    private Order order2;

    @BeforeEach
    public void setUp(){
        // Mock 객체 생성
        product1 = mock(Product.class);
        product2 = mock(Product.class);
        member1 = mock(Member.class);

        orderProduct1 = OrderProduct.builder()
                .product(product1)
                .orderProductPrice(1000)
                .orderProductCount(1)
                .build();

        orderProduct2 = OrderProduct.builder()
                .product(product2)
                .orderProductPrice(2000)
                .orderProductCount(2)
                .build();

        // Order 객체 생성 및 주문 상품 추가
        order1 = Order.builder()
                .orderStatus(OrderStatus.ACCEPTED)
                .member(member1)
                .orderProducts(Arrays.asList(orderProduct1, orderProduct2))
                .build();
        order2 = Order.builder()
                .orderStatus(CANCELED)
                .member(member1)
                .orderProducts(Arrays.asList(orderProduct1, orderProduct2))
                .build();

        orders.add(order1);
    }

    @Test //수정
    @DisplayName("주문 정보 전체 조회에 성공한다.")
    void getOrders() throws Exception {
        // Given
        List<OrderDto> orderDtos = orders.stream().map(OrderDto::of).collect(Collectors.toList());
        given(orderService.getOrders()).willReturn(orderDtos);

        // When, Then
        mvc.perform(get("/api/v1/orders"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.data[0].orderProducts[0].orderProductPrice").value(1000))
                .andExpect(jsonPath("$.data[0].orderProducts[1].orderProductPrice").value(2000));
    }

    @Test
    @DisplayName("주문 생성에 성공한다.")
    void createOrder() throws Exception {
        given(orderService.createOrder(anyLong(), anyList())).willReturn(1L);

        Long memberId = 1L;
        List<OrderProductCreateDto> createDtos = Arrays.asList(
                new OrderProductCreateDto(1L, 2),
                new OrderProductCreateDto(2L, 1)
        );

        mvc.perform(post("/api/v1/orders/new")
                .param("memberId", memberId.toString())
                .contentType(APPLICATION_JSON)
                .content(asJsonString(createDtos)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.data").value(1L));
    }

    @Test
    @DisplayName("주문 취소에 성공한다.")
    void cancelOrder() throws Exception{
        when(orderService.cancelOrder(anyLong())).thenReturn(OrderDto.of(order2));

        mvc.perform(patch("/api/v1/orders/delete/{id}", 1)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.data.orderStatus").value("CANCELED"));
    }

    private String asJsonString(Object obj) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }
}