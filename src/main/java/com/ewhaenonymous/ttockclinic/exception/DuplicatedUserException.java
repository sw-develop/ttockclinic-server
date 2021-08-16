package com.ewhaenonymous.ttockclinic.exception;

public class DuplicatedUserException extends RuntimeException{
    public DuplicatedUserException(){
        super();
    }

    public DuplicatedUserException(String message){
        super(message);
    }
}
