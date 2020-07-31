package com.thoughtworks.springbootemployee.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table
public class Company {
    public Company(String name){
        this.name = name;
    }
    public Company(){

    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int companyId;

    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "company")
    private List<Employee> employees;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return companyId == company.companyId &&
                Objects.equals(name, company.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(companyId, name);
    }
}
