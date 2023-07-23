package com.example.timesaleapp.domain.product.dto;

import com.example.timesaleapp.domain.product.Category;
import com.example.timesaleapp.domain.product.Tag;

public record ProductUpdateDto(String name, Category category, String mainImage, Integer price, Integer stockQuantity,
                               Tag tag) {
}
