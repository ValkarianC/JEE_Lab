package org.example.jee_lab.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ResourceNotMatchingException extends RuntimeException {
    private String resource;
    private String field;
    private Object requestedValue;
    private Object incomingValue;


    //Incoming ID 'ID' does not match requested ID 'ID'

    public ResourceNotMatchingException(String resource, String field, Object requestedValue, Object incomingValue) {
        super(String.format("Incoming %s %s '%s' does not match requested %s '%s'", resource, field, incomingValue, field, requestedValue));
        this.resource = resource;
        this.field = field;
        this.requestedValue = requestedValue;
        this.incomingValue = incomingValue;
    }

  public String getResource() {
    return resource;
  }

  public String getField() {
    return field;
  }

  public Object getRequestedValue() {
    return requestedValue;
  }

  public Object getIncomingValue() {
    return incomingValue;
  }
}
