package com.example.timesaleapp.service;

import com.example.timesaleapp.domain.timesale.dto.TimeSaleRegisterDto;
import com.example.timesaleapp.domain.product.Product;
import com.example.timesaleapp.domain.timesale.TimeSale;
import com.example.timesaleapp.repository.TimeSaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TimeSaleService {

    private final TimeSaleRepository timeSaleRepository;
    private final ProductService productService;

    @Transactional
    public void register(TimeSaleRegisterDto registerDto){
        Product product = productService.getProduct(registerDto.productId());
        TimeSale timeSale = TimeSale.of(registerDto, product);
        timeSale.applyDiscount();
        timeSaleRepository.save(timeSale);
    }

}
