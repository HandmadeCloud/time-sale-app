package com.example.timesaleapp.domain.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {

    private Long id;
    private String name;
    private Category category;
    private String brand;
    private String description;
    private String productMainImage;
    private List<String> productImages;
    private int price;
    private ProductStatus productStatus;
    private Tag tag;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static ProductDto of(Product product) {
        ProductDto productDto = ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .category(product.getCategory())
                .brand(product.getBrand())
                .description(product.getDescription())
                .productMainImage(product.getProductMainImage())
                .productImages(product.getProductImages())
                .price(product.getPrice())
                .productStatus(product.getProductStatus())
                .tag(product.getTag())
                .build();
        return productDto;
    }
}
