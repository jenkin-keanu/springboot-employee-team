package com.example.demo.controller;

import com.example.demo.entity.Company;
import com.example.demo.entity.Employee;
import com.example.demo.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @GetMapping("/companies")
    public List<Company> findAllCompanies(){
        return companyService.findAllCompanies();
    }

    @GetMapping("/companies/{companyId}")
    public List<Employee> findAllEmployeesInCompany(@PathVariable int companyId){
        return companyService.findAllEmployeesInCompany(companyId);
    }

    @PostMapping("/companies")
    public void addCompany(@RequestBody Company company){
        companyService.addCompany(company);
    }
}
