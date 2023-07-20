package com.example.timesaleapp.controller;

import com.example.timesaleapp.domain.product.Product;
import com.example.timesaleapp.domain.product.dto.ProductDto;
import com.example.timesaleapp.domain.product.dto.ProductRegisterDto;
import com.example.timesaleapp.domain.product.dto.ProductUpdateDto;
import com.example.timesaleapp.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.timesaleapp.domain.product.Category.BOOK;
import static com.example.timesaleapp.domain.product.Category.MAGIC;
import static com.example.timesaleapp.domain.product.ProductStatus.*;
import static com.example.timesaleapp.domain.product.ProductStatus.ON_SALE;
import static com.example.timesaleapp.domain.product.Tag.FREE_DELIVERY;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductService productService;

    private List<Product> products;
    private ProductRegisterDto registerDto;
    private ProductUpdateDto updateDto;
    private Product product1;
    private Product product2;

    @BeforeEach
    public void setUp(){
        registerDto = new ProductRegisterDto("헤르미온느의 책", BOOK, "harrypotterImage", 100000, 1000, FREE_DELIVERY);
        updateDto = new ProductUpdateDto("해리포터 아빠 지팡이", null, null, null, null, null);

        product1 = Product.builder()
                                .name("해리포터 아빠 지팡이")
                                .category(MAGIC)
                                .mainImage("http://naver.com")
                                .price(1000)
                                .stockQuantity(100)
                                .productStatus(ON_SALE)
                                .tag(FREE_DELIVERY)
                                .build();

        product2 = Product.builder()
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
    @DisplayName("상품 전체 조회에 성공한다.")
    void getProducts() throws Exception {
        //given
        List<ProductDto> productDtos = products.stream().map(ProductDto::of).collect(Collectors.toList());
        given(productService.getProducts()).willReturn(productDtos);

        //when,then
        mvc.perform(get("/api/v1/products"))
                    .andExpect(status().isOk()) // 응답을 기대하는 부분
                    .andExpect(jsonPath("$.data").isArray())
                    .andExpect(jsonPath("$.data[0].name").value("해리포터 지팡이"))
                    .andExpect(jsonPath("$.data[1].stockQuantity").value(200));

    }

    @Test
    @DisplayName("상품 등록에 성공한다.")
    void registerProduct() throws Exception{
        //given
        given(productService.registerProduct(any(ProductRegisterDto.class))).willReturn(1L);
        //when,then
        mvc.perform(post("/api/v1/products/new")
                        .contentType(APPLICATION_JSON)
                        .content(asJsonString(registerDto)))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType(APPLICATION_JSON))
                        .andExpect(jsonPath("$.data").value(1L));
    }

    @Test
    @DisplayName("상품정보 수정에 성공한다.")
    void updateProduct() throws Exception{
        //given
        given(productService.updateProduct(anyLong(), any(ProductUpdateDto.class))).willReturn(ProductDto.of(product1));
        //when,then
        mvc.perform(patch("/api/v1/products/update/{id}", 1)
                        .contentType(APPLICATION_JSON)
                        .content(asJsonString(updateDto)))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType(APPLICATION_JSON))
                        .andExpect(jsonPath("$.data.name").value("해리포터 아빠 지팡이"));
    }

    @Test
    @DisplayName("상햪 정보 삭제 상태변경에 성공한다.")
    void deleteProduct() throws Exception {
        //given
        given(productService.deleteProduct(anyLong())).willReturn(ProductDto.of(product2));

        //when,then
        mvc.perform(patch("/api/v1/products/delete/{id}",2)
                        .contentType(APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType(APPLICATION_JSON))
                        .andExpect(jsonPath("$.data.productStatus").value("NOT_FOR_SALE"));

    }

    private String asJsonString(Object obj) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }
}