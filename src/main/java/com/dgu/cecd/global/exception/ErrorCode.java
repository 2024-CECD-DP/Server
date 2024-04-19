package com.dgu.cecd.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    /* 400 BAD_REQUEST : 잘못된 요청 */
    PasswordNotFoundException(BAD_REQUEST, 400, "비밀번호가 일치하지 않습니다."),

    /* 401 UNAUTHORIZED : 인증되지 않은 사용자 */
    INVALID_AUTH_TOKEN(UNAUTHORIZED, 401, "권한 정보가 없는 토큰입니다."),

    /* 404 NOT_FOUND : Resource 를 찾을 수 없음 */
    USER_NAME_NOT_FOUND(NOT_FOUND, 404, "해당하는 유저가 없습니다."),
    USER_NOT_REGISTERED(NOT_FOUND,404, "가입되지 않은 유저입니다"),

    /* 409 CONFLICT : Resource의 현재 상태와 충돌. 보통 중복된 데이터 존재*/
    USER_DUPLICATE_RESOURCE(CONFLICT, 409, "이미 가입된 이메일입니다.");


    private final HttpStatus httpStatus; // status phrase
    private final int errorCode; // status code
    private final String detail; // message


}
