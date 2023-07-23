package com.example.timesaleapp.controller;

import com.example.timesaleapp.config.ResponseTemplate;
import com.example.timesaleapp.constant.ResponseTemplateStatus;
import com.example.timesaleapp.domain.order.dto.OrderDto;
import com.example.timesaleapp.domain.order.dto.OrderProductCreateDto;
import com.example.timesaleapp.domain.order.dto.OrderProductUpdateDto;
import com.example.timesaleapp.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.timesaleapp.constant.ResponseTemplateStatus.SUCCESS;

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

    @GetMapping
    public ResponseTemplate<List<OrderDto>> getOrders() {

        return ResponseTemplate.valueOf(orderService.getOrders());
    }

    @PatchMapping("/{orderId}")
    public ResponseTemplate<Void> updateOrder(@PathVariable Long orderId, OrderProductUpdateDto updateDto){
        orderService.updateOrder(orderId,updateDto);

        return ResponseTemplate.of(SUCCESS);
    }

    @PatchMapping("/delete/{orderId}")
    public ResponseTemplate<OrderDto> cancelOrder(@PathVariable Long orderId) {

        return ResponseTemplate.valueOf(orderService.cancelOrder(orderId));
    }

}
