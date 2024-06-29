package org.afterfilm.afterfilm.exception.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // common
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "500", "서버 내부 에러"),
    EMAIL_DUPLICATION(HttpStatus.CONFLICT, "409", "이미 존재하는 이메일입니다."),
    USER_DUPLICATION(HttpStatus.CONFLICT, "409", "이미 존재하는 사용자입니다."),
    NO_USER(HttpStatus.NOT_FOUND, "404", "없는 사용자입니다."),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "400", "비밀번호가 옳지 않습니다."),
    NO_LOGIN(HttpStatus.UNAUTHORIZED, "401", "로그인이 필요합니다"),
    NO_TOKEN(HttpStatus.UNAUTHORIZED, "401", "Refresh Token 이 유효하지 않습니다.");


    private HttpStatus httpStatus;
    private String code;
    private String message;
}
