package com.builterdev.apiblog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException{

    private String entity;
    private String field;
    private long value;

    public ResourceNotFoundException(String entity, String field, long value) {
        super(String.format("%s not found resource with %s : %s", entity, field, value));
        this.entity = entity;
        this.field = field;
        this.value = value;
    }

    public String getEntity(){
        return entity;
    }

    public String getField() {
        return field;
    }

    public long getValue() {
        return value;
    }

}
