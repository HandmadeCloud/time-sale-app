package com.example.timesaleapp.domain.member;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Status {

    ACTIVE("Active",0),
    DELETED("Deleted",1);

    private final String statusKey;
    private final int statusNum;

}