package com.example.timesaleapp.controller.advice;

import com.example.timesaleapp.config.ResponseException;
import com.example.timesaleapp.config.ResponseTemplate;
import com.example.timesaleapp.constant.ResponseTemplateStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import java.io.IOException;

@RestControllerAdvice("com.example.timesaleapp.controller")
@Slf4j
public class ControllerAdvice {

    /**
     * 일반 응답 에러
     */
    @ExceptionHandler(ResponseException.class)
    protected ResponseEntity<ResponseTemplate<ResponseTemplateStatus>> expect(ResponseException e){
        e.printStackTrace();
        log.info("Controller advice exception : {}", e);
        ResponseTemplateStatus status = e.getResponseTemplateStatus();
        return ResponseEntity.status(status.getHttpStatus())
                .body(ResponseTemplate.of(status));
    }

    /**
     * 전체 응답 에러
     */
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ResponseTemplate<Void>> expect(Exception e){
        e.printStackTrace();
        log.info("Controller advice exception : {}", e);
        return ResponseEntity.badRequest()
                .body(ResponseTemplate.error(ResponseTemplateStatus.LOGICAL_ERROR));
    }

    /**
     * 헤더 요청이 올바르지 않은 경우
     */
    @ExceptionHandler(MissingRequestHeaderException.class)
    protected ResponseEntity<ResponseTemplate<Void>> handleMissingRequestHeaderException(MissingRequestHeaderException e) {
        log.error("Excepted MissingRequestHeaderException : {}", e.getMessage());
        return ResponseEntity.badRequest()
                .body(ResponseTemplate.error(ResponseTemplateStatus.LOGICAL_ERROR));
    }

    /**
     * body 요청이 올바르지 않은 경우
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<ResponseTemplate<Void>> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error("Excepted MissingRequestBodyException : {}", e.getMessage());
        return ResponseEntity.badRequest()
                .body(ResponseTemplate.error(ResponseTemplateStatus.LOGICAL_ERROR));
    }

    /**
     * 잘못된 주소료 요청을 한 경우
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    protected ResponseEntity<ResponseTemplate<Void>> handleNoHandlerFoundExceptionException(NoHandlerFoundException e) {
        log.error("handleNoHandlerFoundException : {}", e.getMessage());
        return ResponseEntity.badRequest()
                .body(ResponseTemplate.error(ResponseTemplateStatus.NOT_FOUND));
    }

    /**
     * null 값이 발생한 경우
     */
    @ExceptionHandler(IOException.class)
    protected ResponseEntity<ResponseTemplate<Void>> expect(IOException e){
        log.error("handleIOException", e);
        return ResponseEntity.badRequest()
                .body(ResponseTemplate.error(ResponseTemplateStatus.NOT_FOUND));
    }
}
