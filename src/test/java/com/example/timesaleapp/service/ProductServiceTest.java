package com.example.timesaleapp.service;

import com.example.timesaleapp.domain.product.Product;
import com.example.timesaleapp.domain.product.dto.ProductDto;
import com.example.timesaleapp.domain.product.dto.ProductRegisterDto;
import com.example.timesaleapp.domain.product.dto.ProductUpdateDto;
import com.example.timesaleapp.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.example.timesaleapp.domain.product.Category.MAGIC;
import static com.example.timesaleapp.domain.product.ProductStatus.NOT_FOR_SALE;
import static com.example.timesaleapp.domain.product.ProductStatus.ON_SALE;
import static com.example.timesaleapp.domain.product.Tag.FREE_DELIVERY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private Product product1;
    private Product product2;
    private List<Product> products;

    @BeforeEach
    public void setUp(){
        product1 = Product.builder()
                .productId(1L)
                .name("해리포터 지팡이")
                .category(MAGIC)
                .mainImage("http://naver.com")
                .price(1000)
                .stockQuantity(100)
                .productStatus(ON_SALE)
                .tag(FREE_DELIVERY)
                .build();

        product2 = Product.builder()
                .productId(2L)
                .name("볼드모트 지팡이")
                .category(MAGIC)
                .mainImage("http://naver2.com")
                .price(10000)
                .stockQuantity(200)
                .productStatus(NOT_FOR_SALE)
                .tag(FREE_DELIVERY)
                .build();

        products = Arrays.asList(product1, product2);
    }

    @Test
    @DisplayName("상품 등록에 성공한다.")
    void register() {
        //given
        ProductRegisterDto registerDto = new ProductRegisterDto("해리포터 지팡이", MAGIC, "http://naver.com",
                                                                1000, 100, FREE_DELIVERY);
        given(productRepository.save(any(Product.class))).willReturn(product1);
        //when
        Long productId = productService.registerProduct(registerDto);
        //then
        assertThat(productId).isEqualTo(1L);
    }

    @Test
    @DisplayName("전체 상품 조회에 성공한다.")
    void getProducts() {
        //given
        given(productRepository.findAll()).willReturn(products);
        //when
        List<ProductDto> allProducts = productService.getProducts();
        //then
        assertThat(allProducts.get(0).getPrice()).isEqualTo(1000);
        assertThat(allProducts.get(1).getMainImage()).isEqualTo("http://naver2.com");
    }

//    @Test : 타임세일용
//    void getProduct() {
//    }

    @Test
    @DisplayName("상품 정보 수정에 성공한다.")
    void correct() {
        //given
        ProductUpdateDto updateDto = new ProductUpdateDto("해리포터 아빠 지팡이", MAGIC, "http://naver2.com", 1000, 100, FREE_DELIVERY);
        given(productRepository.findById(anyLong())).willReturn(Optional.ofNullable(product1));
        //when
        ProductDto updatedProductDto = productService.updateProduct(1L, updateDto);
        //then
        assertThat(updatedProductDto.getName()).isEqualTo("해리포터 아빠 지팡이");
        assertThat(updatedProductDto.getMainImage()).isEqualTo("http://naver2.com");
    }

    @Test
    @DisplayName("상품 삭제에 성공한다.")
    void deleteProduct() {
        //given
        given(productRepository.findById(anyLong())).willReturn(Optional.ofNullable(product2));
        //when
        ProductDto deletedProductDto = productService.deleteProduct(2L);
        //then
        assertThat(deletedProductDto.getProductStatus()).isEqualTo(NOT_FOR_SALE);
    }
}