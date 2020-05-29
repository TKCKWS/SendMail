package com.example.demo.sendmail.api.advice;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.demo.sendmail.api.ErrorResponseBody;
import com.example.demo.sendmail.exception.ReservationNotFoundException;

@RestControllerAdvice
public class SendMailResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    // MethodArgumentNotValidExceptionの捕捉(Spring Bootのエラー)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        return this.handleExceptionInternal(exception,
                createErrorResponseBody(exception, request),
                null,
                HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception exception, Object body,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        System.out.println("handleExceptionInternal : " + exception.getMessage());

        return this.handleExceptionInternal(exception, body, headers, status, request);
    }

    // MethodArgumentNotValidExceptionの捕捉
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(Exception exception, WebRequest request) {
        return this.handleExceptionInternal(exception,
                createErrorResponseBody(exception, request),
                null,
                HttpStatus.BAD_REQUEST,
                request);
    }

    // 予約が見つからないExceptionの捕捉
    @ExceptionHandler(ReservationNotFoundException.class)
    public ResponseEntity<Object> handleSendMailException(Exception exception, WebRequest request) {
        HttpHeaders headers = new HttpHeaders();

        return super.handleExceptionInternal(exception,
                createErrorResponseBody(exception, request),
                headers,
                HttpStatus.BAD_REQUEST,
                request);
    }

    // 未定義のExceptionを捕捉
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllException(Exception exception, WebRequest request) {
        return this.handleExceptionInternal(exception, createErrorResponseBody(exception, request),
                null,
                HttpStatus.INTERNAL_SERVER_ERROR,
                request);
    }

    // レスポンスのボディ部を作成
    private ErrorResponseBody createErrorResponseBody(Exception exception, WebRequest request) {

        ErrorResponseBody errorResponseBody = new ErrorResponseBody();
        errorResponseBody.setMessage(exception.getMessage());

        return errorResponseBody;
    }
}
