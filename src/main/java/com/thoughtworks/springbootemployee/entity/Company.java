package com.thoughtworks.springbootemployee.entity;

import java.util.ArrayList;
import java.util.List;

public class Company {
    private int companyId;
    private List<Employee> employees = new ArrayList<>();

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
