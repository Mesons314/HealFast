package com.HealQueue.Exceptions;

public class AppointmentDoesNotExistsException extends RuntimeException{
    public AppointmentDoesNotExistsException(String msg){
        super(msg);
    }
}
