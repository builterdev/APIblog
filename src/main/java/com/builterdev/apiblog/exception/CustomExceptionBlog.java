package com.builterdev.apiblog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class CustomExceptionBlog extends RuntimeException{

    private HttpStatus status;
    private String message;

    public CustomExceptionBlog(HttpStatus status,String message){
        this.status = status;
        this.message = message;
    }

    public CustomExceptionBlog(String message, HttpStatus status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }

    public HttpStatus getStatus(){
        return status;
    }

    public String getMessage() {
        return message;
    }

}
