package com.thoughtworks.springbootemployee.exception;

import com.sun.xml.bind.v2.model.core.ID;

public class UnknownCompanyException extends RuntimeException {
    private int companyId;
    private String msg;
    public UnknownCompanyException(int companyId,String msg){
        this.companyId = companyId;
        this.msg = msg;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
