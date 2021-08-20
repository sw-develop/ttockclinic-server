package com.ewhaenonymous.ttockclinic.payload;

public final class ResponseMessage {
    private ResponseMessage(){
    }

    public static final String INVALID_QR = "유효하지 않은 QR 입니다.";

    public static final String DUPLICATED_USER = "중복된 사용자 입니다.";

    public static final String INVALID_USER = "유효하지 않은 사용자 입니다.";
}
