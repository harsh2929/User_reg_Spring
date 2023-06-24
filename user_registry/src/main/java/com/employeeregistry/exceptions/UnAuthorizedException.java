package com.employeeregistry.exceptions;

public class UnAuthorizedException extends Exception {
    public UnAuthorizedException(String msg){
        super(msg);
    }
}
