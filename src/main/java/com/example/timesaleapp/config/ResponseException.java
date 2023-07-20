package com.example.timesaleapp.config;

import com.example.timesaleapp.constant.ResponseTemplateStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ResponseException extends RuntimeException {

    private ResponseTemplateStatus responseTemplateStatus;
}
