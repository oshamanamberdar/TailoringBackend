package com.mdstailor.tailoringbackend.exceptions.CustomerNotFoundException;

public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(String message){
        super(message);
    }
}
