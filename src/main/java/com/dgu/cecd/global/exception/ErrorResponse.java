package com.dgu.cecd.global.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


/**
 *  {
 *      "code" : "400",
 *      "message" : 잘못된 요청입니다.",
 *      "validation" : {
 *          "title" : "값을 입력해주세요."
 *  *      }
 *  }
 */
@Getter
@AllArgsConstructor
public class ErrorResponse {

    private final String code;
    private final String message;

    @Builder
    public ErrorResponse(CustomException exception) {
        this.code = String.valueOf(exception.getErrorCode().getHttpStatus());
        this.message = exception.getErrorMessage();
    }
}
