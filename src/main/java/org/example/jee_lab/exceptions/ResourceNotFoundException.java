package org.example.jee_lab.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    private String resource;
    private String field;
    private Object value;


    public ResourceNotFoundException(String resource, String field, Object value) {
        super(String.format("%s with %s '%s' not found", resource, field, value));
        this.resource = resource;
        this.field = field;
        this.value = value;
    }

    public String getResource() {
        return resource;
    }

    public String getField() {
        return field;
    }

    public Object getValue() {
        return value;
    }
}
