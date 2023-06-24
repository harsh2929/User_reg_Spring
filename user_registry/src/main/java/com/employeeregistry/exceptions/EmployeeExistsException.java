package com.employeeregistry.exceptions;



public class EmployeeExistsException extends Exception {

    public EmployeeExistsException(String msg){
        super(msg);
    }
}
