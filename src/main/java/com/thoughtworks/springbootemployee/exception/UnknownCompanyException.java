package com.thoughtworks.springbootemployee.exception;

import com.sun.xml.bind.v2.model.core.ID;

public class UnknownCompanyException extends RuntimeException {
    private int companyId;

    public UnknownCompanyException(int companyId){
        this.companyId = companyId;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }
}
