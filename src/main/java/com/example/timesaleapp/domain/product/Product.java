package com.example.timesaleapp.domain.product;

import com.example.timesaleapp.config.ResponseException;
import com.example.timesaleapp.constant.ResponseTemplateStatus;
import com.example.timesaleapp.domain.BaseTimeEntity;
import com.example.timesaleapp.domain.product.dto.ProductRegisterDto;
import com.example.timesaleapp.domain.product.dto.ProductUpdateDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import static com.example.timesaleapp.domain.product.ProductStatus.ON_SALE;

@Getter
@Builder
@Entity
@DynamicUpdate
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
public class Product extends BaseTimeEntity {

    private static final int ZERO_QUANTITY = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    private String name;

    @Enumerated(EnumType.STRING)
    private Category category;

    private String mainImage;

    private int price;

    private int stockQuantity;

    @Enumerated(EnumType.STRING)
    private ProductStatus productStatus;

    @Enumerated(EnumType.STRING)
    private Tag tag;

    public void changeStatusSoldOut() {
        this.productStatus = ProductStatus.SOLD_OUT;
    }

    public void changeStatusNotForSale() {
        this.productStatus = ProductStatus.NOT_FOR_SALE;
    }

    public void update(ProductUpdateDto productUpdateDto) {
        this.name = validateUpdateString(productUpdateDto.name(), this.name);
        this.mainImage = validateUpdateString(productUpdateDto.mainImage(), this.mainImage);
        this.price = validateUpdateInt(productUpdateDto.price(), this.price);
        this.stockQuantity = validateUpdateInt(productUpdateDto.stockQuantity(), this.stockQuantity);
        this.tag = validateUpdateTag(productUpdateDto.tag(), this.tag);
    }

    public void deductStock(int orderQuantity) {
        int remainQuantity = this.stockQuantity - orderQuantity;
        if (remainQuantity < ZERO_QUANTITY) {
            throw new ResponseException(ResponseTemplateStatus.NOT_FOUND);
        }
        this.stockQuantity = remainQuantity;
    }

    public void addStock(int canceledQuantity) {
        this.stockQuantity += canceledQuantity;
    }

    public static Product of(ProductRegisterDto registerDto) {
        return Product.builder()
                .name(registerDto.name())
                .category(registerDto.category())
                .mainImage(registerDto.mainImage())
                .price(registerDto.price())
                .stockQuantity(registerDto.stockQuantity())
                .tag(registerDto.tag())
                .productStatus(ON_SALE)
                .build();
    }

    public void changePrice(int salePrice) {
        int originalPrice = this.price;
        this.price = salePrice;
    }

    //수정 업데이트 파트
    private String validateUpdateString(String update, String origin) {
        if (update == null) {
            return origin;
        }
        return update;
    }

    private int validateUpdateInt(Integer update, Integer origin) {
        if (update == null) {
            return origin;
        }
        return update;
    }

    private Tag validateUpdateTag(Tag update, Tag origin) {
        if (update == null) {
            return origin;
        }
        return update;
    }

}
