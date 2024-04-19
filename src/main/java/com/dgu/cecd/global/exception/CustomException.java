package com.dgu.cecd.global.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

    private ErrorCode errorCode;
    private String errorMessage;

    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomException(ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.errorMessage = errorCode.getDetail();
    }

//    //@Valid 오류처리
//    public CustomException(ErrorCode errorCode, String errorMessage) {
//        this.errorCode = errorCode;
//        this.errorMessage = errorMessage;
//    }
}
