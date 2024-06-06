package org.afterfilm.afterfilm.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class SuccessResponse {

    private HttpStatus httpStatus;
    private String code;
    private Object response;

    public SuccessResponse(Object response) {
        this.httpStatus = HttpStatus.OK;
        this.code = "200";
        this.response = response;
    }
}
