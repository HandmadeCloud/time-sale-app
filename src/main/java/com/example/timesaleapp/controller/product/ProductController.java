package com.example.timesaleapp.controller.product;

import com.example.timesaleapp.domain.member.MemberDto;
import com.example.timesaleapp.domain.product.Product;
import com.example.timesaleapp.domain.product.ProductDto;
import com.example.timesaleapp.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/create")
    public String getRegisterPage(){
        return "create";
    }

    @GetMapping("/products")
    public List<Product> getProductsPage(){
        return productService.getAllProducts();
    }

    @PostMapping("/create")
    public String registerProduct(@RequestBody ProductRegisterDto registerDto){
        productService.register(registerDto);
        return "redirect:/products";
    }

    @PatchMapping("/{id}/update")
    public String correctProduct(@PathVariable Long id, @RequestBody ProductUpdateDto productUpdateDto ){
        ProductDto productDto = productService.correct(id, productUpdateDto);
        return "redirect:/products";
    }

    @PatchMapping("/{id}/delete")
    public String deleteMember(@PathVariable Long id){
        ProductDto memberDto = productService.delete(id);
        return "redirect:/";
    }
}
