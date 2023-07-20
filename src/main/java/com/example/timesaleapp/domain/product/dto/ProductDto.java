package com.example.timesaleapp.domain.product.dto;

import com.example.timesaleapp.domain.product.Category;
import com.example.timesaleapp.domain.product.Product;
import com.example.timesaleapp.domain.product.ProductStatus;
import com.example.timesaleapp.domain.product.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class ProductDto {

    private Long productId;
    private String name;
    private Category category;
    private String mainImage;
    private int price;
    private int stockQuantity;
    private ProductStatus productStatus;
    private Tag tag;

    public static ProductDto of(Product product) {

        return ProductDto.builder()
                .productId(product.getProductId())
                .name(product.getName())
                .category(product.getCategory())
                .mainImage(product.getMainImage())
                .price(product.getPrice())
                .stockQuantity(product.getStockQuantity())
                .productStatus(product.getProductStatus())
                .tag(product.getTag())
                .build();
    }
}
