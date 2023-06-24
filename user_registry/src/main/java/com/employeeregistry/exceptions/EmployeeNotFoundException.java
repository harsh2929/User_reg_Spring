package com.employeeregistry.exceptions;

public class EmployeeNotFoundException extends Exception {
    public EmployeeNotFoundException(String msg){
        super(msg);
    }
}
