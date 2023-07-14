package com.example.timesaleapp.controller.product;

import com.example.timesaleapp.domain.product.Category;
import com.example.timesaleapp.domain.product.Product;
import com.example.timesaleapp.domain.product.Tag;
import java.util.List;

public record ProductRegisterDto(String name, String category, String brand, String description, String productMainImage,
                                 List<String> productImages, int price, String tag) {
    public Product toEntity(){
        return Product.builder()
                .name(name)
                .category(Category.valueOf(category))
                .brand(brand)
                .description(description)
                .productMainImage(productMainImage)
                .productImages(productImages)
                .price(price)
                .tag(Tag.valueOf(tag))
                .build();
    }
}
