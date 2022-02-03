package com.mdstailor.tailoringbackend.exceptions.BillNotFoundException;

public class BillNotFoundException extends RuntimeException{
    public BillNotFoundException(String message) {
        super(message);
    }
}
