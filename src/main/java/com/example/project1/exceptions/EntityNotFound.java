package com.example.project1.exceptions;

public class EntityNotFound extends RuntimeException {

    public EntityNotFound(String message){
        super(message);
    }
    
}
