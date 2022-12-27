package com.example.project1.exceptions;

public class AuthenticationFailed extends RuntimeException {
    public AuthenticationFailed(String message){
        super(message);
    }
    
}
