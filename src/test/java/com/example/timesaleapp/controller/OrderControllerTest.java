package com.example.timesaleapp.controller;

import com.example.timesaleapp.domain.order.Order;
import com.example.timesaleapp.domain.order.dto.OrderProductCreateDto;
import com.example.timesaleapp.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(OrderController.class)
class OrderControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private OrderService orderService;

    private List<Order> orders;
    private OrderProductCreateDto createDto;

    @BeforeEach
    public void setUp(){


    }

    @Test
    @DisplayName("주문 정보 전체 조회에 성공한다.")
    void getAll() {
    }

    @Test
    @DisplayName("주문 생성에 성공한다.")
    void create() {

    }

    @Test
    @DisplayName("주문 취소에 성공한다.")
    void cancel() {
    }
}