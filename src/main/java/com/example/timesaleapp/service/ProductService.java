package com.example.timesaleapp.service;

import com.example.timesaleapp.config.MyAppNotFoundException;
import com.example.timesaleapp.domain.product.dto.ProductRegisterDto;
import com.example.timesaleapp.domain.product.dto.ProductUpdateDto;
import com.example.timesaleapp.domain.product.Product;
import com.example.timesaleapp.domain.product.dto.ProductDto;
import com.example.timesaleapp.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service @Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public Long register(ProductRegisterDto registerDto) {
        Product product = Product.of(registerDto);
        productRepository.save(product);
        return product.getId();
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Product getProduct(Long productId) {
        return productRepository.findById(productId).orElseThrow(MyAppNotFoundException::new);
    }

    public ProductDto correct(Long id, ProductUpdateDto productUpdateDto) {
        Product product = productRepository.findById(id).orElseThrow(MyAppNotFoundException::new);
        log.info(product.getTag().toString());
        product.update(productUpdateDto);
        log.info(product.getMainImage());
        log.info(product.getTag().toString());
        return ProductDto.of(product);
    }

    public ProductDto delete(Long id) {
        Product product = productRepository.findById(id).orElseThrow(MyAppNotFoundException::new);
        product.changeStatusNotForSale();
        return ProductDto.of(product);
    }

}
