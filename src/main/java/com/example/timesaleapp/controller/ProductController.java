package com.example.timesaleapp.controller;

import com.example.timesaleapp.config.ResponseTemplate;
import com.example.timesaleapp.domain.product.Product;
import com.example.timesaleapp.domain.product.dto.ProductDto;
import com.example.timesaleapp.domain.product.dto.ProductRegisterDto;
import com.example.timesaleapp.domain.product.dto.ProductUpdateDto;
import com.example.timesaleapp.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping("")
    public ResponseTemplate<List<ProductDto>> getProducts() {

        return ResponseTemplate.valueOf(productService.getProducts());
    }

    @PostMapping("/new")
    public ResponseTemplate<Long> registerProduct(@RequestBody ProductRegisterDto registerDto) {

        return ResponseTemplate.valueOf(productService.registerProduct(registerDto));
    }

    @PatchMapping("/update/{productId}")
    public ResponseTemplate<ProductDto> updateProduct(@PathVariable Long productId, @RequestBody ProductUpdateDto productUpdateDto) {

        return ResponseTemplate.valueOf(productService.updateProduct(productId, productUpdateDto));
    }

    @PatchMapping("/delete/{productId}")
    public ResponseTemplate<ProductDto> deleteProduct(@PathVariable Long productId) {

        return ResponseTemplate.valueOf(productService.deleteProduct(productId));
    }
}
