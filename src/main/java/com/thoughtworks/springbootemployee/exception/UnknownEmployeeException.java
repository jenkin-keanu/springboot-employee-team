package com.thoughtworks.springbootemployee.exception;

public class UnknownEmployeeException extends RuntimeException{
    private int employeeId;
    private String errorMsg;

    public UnknownEmployeeException(int employeeId, String errorMsg) {
        this.employeeId = employeeId;
        this.errorMsg = errorMsg;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
