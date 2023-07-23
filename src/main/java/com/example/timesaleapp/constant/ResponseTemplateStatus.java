package com.example.timesaleapp.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public enum ResponseTemplateStatus {

    SUCCESS("T_2000", "요청 성공"),

    /**
     * member 관련 오류
     */

    EMAIL_FORM_INVALID("T_3000", "유효하지 않은 이메일 형식입니다."),
    PASSWORD_FORM_INVALID("T_3001", "유효하지 않은 비밀번호 형식입니다."),
    EMAIL_DUPLICATE("T_3002", "중복된 이메일입니다."),

    /**
     * product 관련 오류
     */
    PRODUCT_MINUS_STOCK("T_4001", "재고가 부족합니다."),

    /**
     * timesale 관련 오류
     */
    SALE_PRICE_NEGATIVE("T_5001", "가격이 음수입니다."),

    /**
     * 전체 오류
     */
    NOT_FOUND("T_9001", "해당하는 정보가 없습니다."),
    LOGICAL_ERROR("T_9002", "서버 내부 에러");

    private final String code;
    private final String message;


}
