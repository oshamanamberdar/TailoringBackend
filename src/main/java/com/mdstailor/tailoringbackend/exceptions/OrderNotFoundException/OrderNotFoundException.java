package com.mdstailor.tailoringbackend.exceptions.OrderNotFoundException;

public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException (String message){
        super(message);
    }
}
