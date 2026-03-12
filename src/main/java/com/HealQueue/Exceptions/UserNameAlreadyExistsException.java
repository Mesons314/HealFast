package com.HealQueue.Exceptions;

public class UserNameAlreadyExistsException extends RuntimeException{
    public UserNameAlreadyExistsException(String msg){
        super(msg);
    }
}
