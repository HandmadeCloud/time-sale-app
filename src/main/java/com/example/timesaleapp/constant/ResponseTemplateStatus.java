package com.example.timesaleapp.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public enum ResponseTemplateStatus {

    SUCCESS(HttpStatus.OK, 200, "요청 성공"),

    /**
     * member 관련 오류
     */

    EMAIL_FORM_INVALID(HttpStatus.BAD_REQUEST, 300, "유효하지 않은 이메일 형식입니다."),
    PASSWORD_FORM_INVALID(HttpStatus.BAD_REQUEST, 301, "유효하지 않은 비밀번호 형식입니다."),
    EMAIL_DUPLICATE(HttpStatus.BAD_REQUEST, 302, "중복된 이메일입니다."),

    /**
     * product 관련 오류
     */
    PRODUCT_MINUS_STOCK(HttpStatus.NOT_ACCEPTABLE, 400, "재고가 부족합니다."),

    /**
     * timesale 관련 오류
     */
    SALE_PRICE_NEGATIVE(HttpStatus.NOT_ACCEPTABLE, 500, "가격이 음수입니다."),

    /**
     * 전체 오류
     */
    NOT_FOUND(HttpStatus.NOT_FOUND, 900, "해당하는 정보가 없습니다."),
    LOGICAL_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 901, "서버 내부 에러");

    private HttpStatus httpStatus;
    private final int code;
    private final String message;


}
