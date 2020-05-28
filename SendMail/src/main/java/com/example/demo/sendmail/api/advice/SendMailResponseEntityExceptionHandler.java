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

@RestControllerAdvice
public class SendMailResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    // MethodArgumentNotValidExceptionの捕捉
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
            HttpHeaders headers, HttpStatus status, WebRequest request) {
        return super.handleExceptionInternal(exception,
                createErrorResponseBody(exception, request),
                null,
                HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception exception, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        System.out.println("handleExceptionInternal : " + exception.getMessage());

        return super.handleExceptionInternal(exception, body, headers, status, request);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(Exception exception, WebRequest request) {
        return super.handleExceptionInternal(exception, "IllegalArgumentException", null, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllException(Exception exception, WebRequest request) {
        return super.handleExceptionInternal(exception, "handleAllException", null, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    // 独自Exceptionの捕捉(独自Exception作成後コメントアウト解除
    //    @ExceptionHandler(Exception.class)
    //    public ResponseEntity<Object> handleSendMailException(Exception exception, WebRequest request) {
    //        HttpHeaders headers = new HttpHeaders();
    //
    //        return super.handleExceptionInternal(exception,
    //                createErrorResponseBody(exception, request),
    //                headers,
    //                HttpStatus.BAD_REQUEST,
    //                request);
    //    }

    // レスポンスのボディ部を作成
    private ErrorResponseBody createErrorResponseBody(Exception exception, WebRequest request) {

        ErrorResponseBody errorResponseBody = new ErrorResponseBody();
        int responseCode = HttpStatus.BAD_REQUEST.value();
        String responseErrorMessage = HttpStatus.BAD_REQUEST.getReasonPhrase();

        errorResponseBody.setStatus(responseCode);
        errorResponseBody.setError(responseErrorMessage);
        errorResponseBody.setMessage(exception.getMessage());

        return errorResponseBody;
    }
}
