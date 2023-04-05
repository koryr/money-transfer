package com.pezesha.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author : HAron Korir
 * {@code @mailto} : koryrh@gmail.com
 * {@code @created} : 4/4/23, Tuesday
 **/
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InsufficientBalanceException extends RuntimeException {

    static final long serialVersionUID = 1L;

    public InsufficientBalanceException(String message) {
        super(message);
    }

}