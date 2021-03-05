package com.example.API.Controller;

public class EmployeeNotFoundException extends Exception{

    private long employeeId;
    public EmployeeNotFoundException(long employeeId) {
        super(String.format("employee is not found with id : '%s'", employeeId));
    }
}
