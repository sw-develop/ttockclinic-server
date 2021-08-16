package com.ewhaenonymous.ttockclinic.exception;

public class InvalidQrException extends RuntimeException{
    public InvalidQrException(){
        super();
    }

    public InvalidQrException(String message){
        super(message);
    }
}
