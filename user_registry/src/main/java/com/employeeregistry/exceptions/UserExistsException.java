package com.employeeregistry.exceptions;

public class UserExistsException extends Exception {
    public UserExistsException(String msg){
        super(msg);
    }
}
