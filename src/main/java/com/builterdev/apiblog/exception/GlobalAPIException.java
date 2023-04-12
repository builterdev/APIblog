package com.builterdev.apiblog.exception;

import com.builterdev.apiblog.payload.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class GlobalAPIException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomExceptionBlog.class)
    public ResponseEntity<ErrorDetails> handleBlogAPIException(CustomExceptionBlog exceptionBlog, WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exceptionBlog.getMessage(), webRequest.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

}
