package com.example.timesaleapp.domain.product;

import com.example.timesaleapp.controller.product.ProductUpdateDto;
import com.example.timesaleapp.domain.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;

@Getter @Builder @Entity
@Table(name="members")
@AllArgsConstructor
@NoArgsConstructor
public class Product extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Category category;

    private String brand;

//    private int productSaved;

    private String description;

    private String productMainImage;

    @ElementCollection
    private List<String> productImages;

    private int price;

//    private Review review;

    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;

    @Enumerated(EnumType.STRING)
    private Tag tag;


    public void changeStatusSoldOut(){
        this.productStatus = ProductStatus.SOLD_OUT;
    }

    public void changeStatusNotForSale(){
        this.productStatus = ProductStatus.NOT_FOR_SALE;
    }

    public void update(ProductUpdateDto productUpdateDto) {
        this.name = productUpdateDto.name();
        this.brand = productUpdateDto.brand();
        this.description = productUpdateDto.description();
        this.productMainImage = productUpdateDto.productMainImage();
        this.productImages = productUpdateDto.productImages();
        this.price = productUpdateDto.price();
    }
}
