package com.ewhaenonymous.ttockclinic.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class InvalidQrException extends RuntimeException{
    public InvalidQrException(){
        super();
    }

    public InvalidQrException(String message){
        super(message);
    }
}
