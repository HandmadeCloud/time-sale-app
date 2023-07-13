package com.example.timesaleapp.config;

import com.example.timesaleapp.domain.Validatable;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.util.Arrays;

@Aspect
@Component
public class ValidationAspect {

    @Around("@annotation(com.example.timesaleapp.annotation.Validation)")
    public Object proceed(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        Arrays.stream(proceedingJoinPoint.getArgs())
                .filter(arg -> arg instanceof Validatable)
                .map(arg -> (Validatable) arg)
                .forEach(Validatable::validate);
        return proceedingJoinPoint.proceed();
    }
}
