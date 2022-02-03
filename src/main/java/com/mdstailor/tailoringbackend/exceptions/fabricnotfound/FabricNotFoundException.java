package com.mdstailor.tailoringbackend.exceptions.fabricnotfound;

public class FabricNotFoundException extends RuntimeException{
    public  FabricNotFoundException(String message){
        super(message);
    }
}
