package org.example.exception;

public class InternalServerErrorException extends jakarta.ws.rs.InternalServerErrorException {
    public InternalServerErrorException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s Internal Server Error %s : '%s'", resourceName, fieldName, fieldValue));
    }

    public InternalServerErrorException(){
        super(String.format("Internal Server Error"));
    }
}
