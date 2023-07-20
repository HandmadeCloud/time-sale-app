package com.example.timesaleapp.controller;

import com.example.timesaleapp.config.ResponseTemplate;
import com.example.timesaleapp.domain.order.Order;
import com.example.timesaleapp.domain.order.dto.OrderDto;
import com.example.timesaleapp.domain.order.dto.OrderProductCreateDto;
import com.example.timesaleapp.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/new")
    public ResponseTemplate<Long> createOrder(@RequestParam("memberId") Long memberId,
                                        @RequestBody List<OrderProductCreateDto> orderProducts) {
        return ResponseTemplate.valueOf(orderService.createOrder(memberId, orderProducts));
    }

    @GetMapping("")
    public ResponseTemplate<List<Order>> getOrders(){
        return ResponseTemplate.valueOf(orderService.getOrders());
    }

//    @PatchMapping("/{id}/update")
//    public ResponseTemplate<OrderDto> updateOrder(@PathVariable Long id){
//        return ResponseTemplate.valueOf(orderService.correct(id));
//    }

    @PatchMapping("/delete/{orderId}")
    public ResponseTemplate<OrderDto> cancelOrder(@PathVariable Long orderId){
        return ResponseTemplate.valueOf(orderService.cancelOrder(orderId));
    }

}
