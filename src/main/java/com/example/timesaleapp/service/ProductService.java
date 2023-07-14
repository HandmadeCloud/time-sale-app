package com.example.timesaleapp.service;

import com.example.timesaleapp.config.MyAppNotFoundException;
import com.example.timesaleapp.controller.product.ProductRegisterDto;
import com.example.timesaleapp.controller.product.ProductUpdateDto;
import com.example.timesaleapp.domain.product.Product;
import com.example.timesaleapp.domain.product.ProductDto;
import com.example.timesaleapp.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public void register(ProductRegisterDto registerDto) {
        Product product = registerDto.toEntity();
        productRepository.save(product);
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }


    public ProductDto correct(Long id, ProductUpdateDto productUpdateDto) {
        Product product = productRepository.findById(id).orElseThrow(MyAppNotFoundException::new);
        product.update(productUpdateDto);
        return ProductDto.of(product);
    }

    public ProductDto delete(Long id) {
        Product product = productRepository.findById(id).orElseThrow(MyAppNotFoundException::new);
        product.changeStatusNotForSale();
        return ProductDto.of(product);
    }
}
