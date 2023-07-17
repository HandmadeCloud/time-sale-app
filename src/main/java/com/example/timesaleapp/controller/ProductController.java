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
@RequestMapping("api/v1/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/create")
    public String getRegisterPage(){
        return "create";
    }

    @GetMapping("/")
    public ResponseTemplate<List<Product>> getProductsPage(){
        return ResponseTemplate.valueOf(productService.getAllProducts());
    }

    @PostMapping("/create")
    public ResponseTemplate<Long> registerProduct(@RequestBody ProductRegisterDto registerDto){
        return ResponseTemplate.valueOf(productService.register(registerDto));
    }

    @PatchMapping("/{id}/update")
    public ResponseTemplate<ProductDto> correctProduct(@PathVariable Long id, @RequestBody ProductUpdateDto productUpdateDto ){
        return ResponseTemplate.valueOf(productService.correct(id, productUpdateDto));
    }

    @PatchMapping("/{id}/delete")
    public ResponseTemplate<ProductDto> deleteMember(@PathVariable Long id){
        return ResponseTemplate.valueOf(productService.delete(id));
    }
}
