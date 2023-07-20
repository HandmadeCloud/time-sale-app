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
import java.util.stream.Collectors;

@Service @Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public Long registerProduct(ProductRegisterDto registerDto) {
        Product product = Product.of(registerDto);

        return productRepository.save(product).getProductId();
    }

    public List<ProductDto> getProducts(){
        List<Product> products = productRepository.findAll();

        return products.stream().map(ProductDto::of).collect(Collectors.toList());
    }

    public Product getProduct(Long productId) {

        return productRepository.findById(productId).orElseThrow(MyAppNotFoundException::new);
    }

    @Transactional
    public ProductDto updateProduct(Long id, ProductUpdateDto productUpdateDto) {
        Product product = productRepository.findById(id).orElseThrow(MyAppNotFoundException::new);
        product.update(productUpdateDto);

        return ProductDto.of(product);
    }

    public ProductDto deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(MyAppNotFoundException::new);
        product.changeStatusNotForSale();

        return ProductDto.of(product);
    }

}
