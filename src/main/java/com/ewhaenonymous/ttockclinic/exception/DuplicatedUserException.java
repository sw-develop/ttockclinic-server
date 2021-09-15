package com.ewhaenonymous.ttockclinic.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DuplicatedUserException extends RuntimeException{
    public DuplicatedUserException(){
        super();
    }

    public DuplicatedUserException(String message){
        super(message);
    }
}
