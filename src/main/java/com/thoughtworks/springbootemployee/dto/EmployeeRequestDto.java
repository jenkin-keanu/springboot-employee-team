package com.thoughtworks.springbootemployee.dto;

//TODO

public class EmployeeRequestDto {

    public EmployeeRequestDto(String name, Integer age, String gender, Integer companyId) {
        this.age = age;
        this.name = name;
        this.gender = gender;
        this.companyId = companyId;
    }
    public EmployeeRequestDto(){

    }
    private Integer id;

    private Integer age;

    private String name;

    private String gender;

    private Integer companyId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
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

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }
}

