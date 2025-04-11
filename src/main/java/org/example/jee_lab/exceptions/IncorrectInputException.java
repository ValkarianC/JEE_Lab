package org.example.jee_lab.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IncorrectInputException extends RuntimeException {
    private String resource;
    private String attribute;
    private Object value;
    private String format;

    public IncorrectInputException(String resource, String attribute, Object value, String format) {
        super(String.format("%s attribute - %s, with value '%s', is formatted incorrectly. Re-enter data with the following format : '%s'", resource, attribute, value, format));
        this.resource = resource;
        this.attribute = attribute;
        this.value = value;
        this.format = format;

    }

    public String getResource() {
        return resource;
    }

    public String getAttribute() {
        return attribute;
    }

    public Object getValue() {
        return value;
    }

    public String getFormat() {
        return format;
    }
}
