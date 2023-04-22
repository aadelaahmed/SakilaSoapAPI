package org.example.exception;

public class EntityAlreadyExistException extends RuntimeException{
    public EntityAlreadyExistException(String errorMsg){
        super(errorMsg);
    }
}
