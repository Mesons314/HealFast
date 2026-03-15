package com.HealQueue.Exceptions;

public class ClinicNotFoundException extends RuntimeException{
    public ClinicNotFoundException(String msg){
        super(msg);
    }
}
