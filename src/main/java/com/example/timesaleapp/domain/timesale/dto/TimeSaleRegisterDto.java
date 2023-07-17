package com.example.timesaleapp.domain.timesale.dto;

import java.time.LocalDateTime;

public record TimeSaleRegisterDto(Long productId,LocalDateTime startTime, LocalDateTime endTime, int salePrice) {

}
