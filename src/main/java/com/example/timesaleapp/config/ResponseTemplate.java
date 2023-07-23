package com.example.timesaleapp.config;

import com.example.timesaleapp.constant.ResponseTemplateStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static com.example.timesaleapp.constant.ResponseTemplateStatus.SUCCESS;

@Getter
@AllArgsConstructor
public class ResponseTemplate<T> { //응답은 에러에 한해서만 수정

    private String message;
    private String code;
    private T data;

    public static <T> ResponseTemplate<T> valueOf(T data) {
        return of(SUCCESS, data);
    }

    //데이터 없음
    public static <T> ResponseTemplate<T> of(ResponseTemplateStatus status) {
        return of(status, null);
    }

    //데이터 전달
    public static <T> ResponseTemplate<T> of(ResponseTemplateStatus status, T data) {
        return new ResponseTemplate<>(status.getMessage(), status.getCode(), data);
    }

    public static ResponseTemplate<Void> error(ResponseTemplateStatus status) {
        return of(status);
    }
}
