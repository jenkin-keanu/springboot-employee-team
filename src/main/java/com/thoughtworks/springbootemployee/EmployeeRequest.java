package com.thoughtworks.springbootemployee;

public class EmployeeRequest {

    private int age;
    private String name;
    private String gender;
    private int companyId;

    public EmployeeRequest(int age, String name, String gender, int companyId) {
        this.age = age;
        this.name = name;
        this.gender = gender;
        this.companyId = companyId;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }
}
