package com.pezesha.payload;

import lombok.Data;

/**
 * @author : HAron Korir
 * {@code @mailto} : koryrh@gmail.com
 * {@code @created} : 4/4/23, Tuesday
 **/
@Data
public class ApiResponse {
    private Integer status;
    private String message;
    private Object result;
    public ApiResponse(){}

    public ApiResponse(Integer status, String message, Object result) {
        this.status = status;
        this.message = message;
        this.result = result;
    }
}
