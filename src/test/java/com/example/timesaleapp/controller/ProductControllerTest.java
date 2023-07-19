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

import static com.example.timesaleapp.domain.product.Category.BOOK;
import static com.example.timesaleapp.domain.product.Category.MAGIC;
import static com.example.timesaleapp.domain.product.ProductStatus.*;
import static com.example.timesaleapp.domain.product.ProductStatus.ON_SALE;
import static com.example.timesaleapp.domain.product.Tag.FREE_DELIVERY;
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

    @BeforeEach
    public void setUp(){
        registerDto = new ProductRegisterDto("헤르미온느의 책", BOOK, "harrypotterImage", 100000, 1000, FREE_DELIVERY);
        updateDto = new ProductUpdateDto("해리포터 아빠 지팡이", null, null, null, null, null);

        Product product1 = Product.builder()
                                .name("해리포터 지팡이")
                                .category(MAGIC)
                                .mainImage("http://naver.com")
                                .price(1000)
                                .stockQuantity(100)
                                .productStatus(ON_SALE)
                                .tag(FREE_DELIVERY)
                                .build();

        Product product2 = Product.builder()
                                .name("볼드모트 지팡이")
                                .category(MAGIC)
                                .mainImage("http://naver2.com")
                                .price(10000)
                                .stockQuantity(200)
                                .productStatus(ON_SALE)
                                .tag(FREE_DELIVERY)
                                .build();

        products = Arrays.asList(product1, product2);
    }

    @Test
    @DisplayName("상품 전체 조회에 성공한다.")
    void getAll() throws Exception {
        //given
        when(productService.getAllProducts()).thenReturn(products);

        //when,then
        mvc.perform(get("/api/v1/products")
                    .contentType(APPLICATION_JSON)) // 요청에 관한 정보 전달
                    .andExpect(status().isOk()) // 응답을 기대하는 부분
                    .andExpect(jsonPath("$.data").isArray())
                    .andExpect(jsonPath("$.data[0].name").value("해리포터 지팡이"))
                    .andExpect(jsonPath("$.data[1].stockQuantity").value(200));

    }

    @Test
    @DisplayName("상품 등록에 성공한다.")
    void register() throws Exception{
        when(productService.register(any(ProductRegisterDto.class))).thenReturn(1L);

        mvc.perform(post("/api/v1/products/new")
                        .contentType(APPLICATION_JSON)
                        .content(asJsonString(registerDto)))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType(APPLICATION_JSON))
                        .andExpect(jsonPath("$.data").value(1L));
    }

    @Test
    @DisplayName("상품정보 수정에 성공한다.")
    void correct() throws Exception{
        //given
        Product updatedProduct = Product.builder()
                                .name("해리포터 아빠 지팡이")
                                .build();

        when(productService.correct(anyLong(), any(ProductUpdateDto.class))).thenReturn(ProductDto.of(updatedProduct));

        //when,then
        mvc.perform(patch("/api/v1/products/update/{id}", 1)
                        .contentType(APPLICATION_JSON)
                        .content(asJsonString(updateDto)))
                        .andExpect(status().isOk())
                        .andExpect(content().contentType(APPLICATION_JSON))
                        .andExpect(jsonPath("$.data.name").value("해리포터 아빠 지팡사"));
    }

    @Test
    @DisplayName("상햪 정보 삭제 상태변경에 성공한다.")
    void delete() throws Exception {
        //given
        Product deletedProduct = Product.builder()
                                .name("호크룩스")
                                .category(MAGIC)
                                .price(1000)
                                .stockQuantity(10)
                                .productStatus(NOT_FOR_SALE)
                                .build();

        when(productService.delete(anyLong())).thenReturn(ProductDto.of(deletedProduct));

        //when,then
        mvc.perform(patch("/api/v1/products/delete/{id}",1)
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