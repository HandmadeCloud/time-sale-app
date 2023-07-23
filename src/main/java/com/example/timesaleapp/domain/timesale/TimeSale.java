package com.example.timesaleapp.domain.timesale;

import com.example.timesaleapp.config.ResponseException;
import com.example.timesaleapp.domain.BaseTimeEntity;
import com.example.timesaleapp.domain.product.Product;
import com.example.timesaleapp.domain.timesale.dto.TimeSaleRegisterDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;

import static com.example.timesaleapp.constant.ResponseTemplateStatus.SALE_PRICE_NEGATIVE;

@Getter
@Builder
@Entity
@Table(name = "time_sales")
@AllArgsConstructor
@NoArgsConstructor
public class TimeSale extends BaseTimeEntity {
    private static final int MIN_DISCOUNT_VALUE = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "time_sale_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    private LocalDateTime startTime;


    private LocalDateTime endTime;

    private int salePrice;

    public static TimeSale of(TimeSaleRegisterDto registerDto, Product product) {
        validatePositive(registerDto.salePrice());
        return TimeSale.builder()
                .product(product)
                .startTime(registerDto.startTime())
                .endTime(registerDto.endTime())
                .salePrice(registerDto.salePrice())
                .build();
    }

    public void applyDiscount(){
        if(isOnSale()){
            getProduct().changePrice(salePrice);
        }
    }


    private boolean isOnSale() {
        LocalDateTime currentTime = LocalDateTime.now();
        return startTime.isBefore(currentTime) && endTime.isAfter(currentTime);
    }

    private static void validatePositive(int salePrice) {
        if (salePrice <= MIN_DISCOUNT_VALUE) throw new ResponseException(SALE_PRICE_NEGATIVE);
    }

}