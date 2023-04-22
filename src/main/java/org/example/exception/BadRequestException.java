package org.example.exception;

public class BadRequestException extends jakarta.ws.rs.BadRequestException {
    public BadRequestException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s bad request with %s : '%s'", resourceName, fieldName, fieldValue));
    }

    public BadRequestException(){
        super(String.format("bad request with"));
    }
}
