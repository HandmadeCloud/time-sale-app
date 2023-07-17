package com.example.timesaleapp.controller;

import com.example.timesaleapp.service.ProductService;
import com.example.timesaleapp.service.TimeSaleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TimeSaleController {

    private final ProductService productService;
    private final TimeSaleService timeSaleService;
}
