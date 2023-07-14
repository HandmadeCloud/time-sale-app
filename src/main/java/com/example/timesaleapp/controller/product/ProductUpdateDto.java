package com.example.timesaleapp.controller.product;

import com.example.timesaleapp.domain.product.Category;
import com.example.timesaleapp.domain.product.Tag;

import java.util.List;

public record ProductUpdateDto(String name, Category category, String brand, String description, String productMainImage,
                               List<String> productImages, int price, Tag tag) {
}
