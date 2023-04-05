package com.pezesha.exception;

import com.pezesha.payload.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.*;

/**
 * @author : HAron Korir
 * {@code @mailto} : koryrh@gmail.com
 * {@code @created} : 4/5/23, Wednesday
 **/
@ControllerAdvice
@Slf4j
public class ApiErrorAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler({AccessDeniedException.class})
    public final ResponseEntity<?> handleUnauthorizedException(AccessDeniedException ex) {
        return error(UNAUTHORIZED, "Unauthorized");
    }
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> handleBadRequestException(BadRequestException e) {
        return error(BAD_REQUEST, e.getLocalizedMessage());
    }

    @ExceptionHandler(AppException.class)
    public ResponseEntity<?> handleAppException(AppException e) {
        return error(INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<?> handleConflict(DataIntegrityViolationException e) {
        String message = NestedExceptionUtils.getMostSpecificCause(e).getMessage();
        return error(CONFLICT, message);
    }
    @ExceptionHandler(InsufficientBalanceException.class)
    public ResponseEntity<?> handleInsufficientBalanceException(InsufficientBalanceException e) {
        String message = NestedExceptionUtils.getMostSpecificCause(e).getMessage();
        return error(BAD_REQUEST, message);
    }

    private ResponseEntity<?> error(HttpStatus status, String msg) {
        return new ResponseEntity<>(
                new ApiResponse(status.value(), msg, null),
                status);
    }
}
