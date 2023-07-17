package com.example.timesaleapp.controller;

import com.example.timesaleapp.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/order")
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/orders")
    public String getOrderPage() {
        return "orders";
    }

    @PostMapping("/create")
    public String createOrder(@RequestParam("memberId") Long memberId,
                              @RequestParam("productId") Long productId,
                              @RequestParam("count") int count) {
        orderService.createOrder(memberId, productId, count);

        return "redirect:/";
    }

}
