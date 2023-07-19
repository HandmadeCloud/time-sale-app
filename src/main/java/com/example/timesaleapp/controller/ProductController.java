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
    public ResponseTemplate<List<Product>> getAll(){
        return ResponseTemplate.valueOf(productService.getAllProducts());
    }

    @PostMapping("/new")
    public ResponseTemplate<Long> register(@RequestBody ProductRegisterDto registerDto){
        return ResponseTemplate.valueOf(productService.register(registerDto));
    }

    @PatchMapping("/update/{id}")
    public ResponseTemplate<ProductDto> correct(@PathVariable Long id, @RequestBody ProductUpdateDto productUpdateDto ){
        return ResponseTemplate.valueOf(productService.correct(id, productUpdateDto));
    }

    @PatchMapping("/delete/{id}")
    public ResponseTemplate<ProductDto> delete(@PathVariable Long id){
        return ResponseTemplate.valueOf(productService.delete(id));
    }
}
