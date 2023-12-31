package com.example.timesaleapp.constant;

import java.util.regex.Pattern;

public class Constant {

    public static final Pattern REGEX_EMAIL = Pattern.compile("^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$");

    public static final Pattern REGEX_PWD = Pattern.compile("(?=.*[0-9])(?=.*[a-z])(?=.*[!@#$%^&+=])(?=\\S+$).{8,20}"); //8자 이상 20자 이하, 숫자 한개이상 특수문자 한개이상 포함 공백 불가
}
