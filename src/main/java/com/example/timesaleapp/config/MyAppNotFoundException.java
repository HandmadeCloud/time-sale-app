package com.example.timesaleapp.config;

import com.example.timesaleapp.constant.ResponseTemplateStatus;

public class MyAppNotFoundException extends ResponseException{
    public MyAppNotFoundException(ResponseTemplateStatus status) {
        super(status);
    }

    public MyAppNotFoundException(){super(ResponseTemplateStatus.NOT_FOUND);}
}
