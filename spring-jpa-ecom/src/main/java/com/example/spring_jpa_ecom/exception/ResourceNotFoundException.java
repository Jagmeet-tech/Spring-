package com.example.spring_jpa_ecom.exception;

public class ResourceNotFoundException extends  RuntimeException{

    public ResourceNotFoundException(String message){
        super(message);
    }

    public ResourceNotFoundException(){
        super("Resource not found.");
    }
}
