package org.example.outsourcing.global.exception.custom;

public class EntityNotMatchedException extends RuntimeException{
    public EntityNotMatchedException(String message) {
        super(message);
    }
}
