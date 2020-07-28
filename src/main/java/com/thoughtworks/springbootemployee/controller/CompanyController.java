package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.service.CompanyService;
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

    @GetMapping("/companies/{companyId}")
    public Company findCompanyById(@PathVariable int companyId) {
        return companyService.findCompanyById(companyId);
    }

    @PutMapping("/companies/{companyId}")
    public Company updateEmployee(@PathVariable int companyId, @RequestBody Company company) {
        return companyService.updateCompany(companyId, company);
    }

    @DeleteMapping("/companies/{companyId}")
    public void deleteEmployeeById(@PathVariable int companyId){
        companyService.deleteCompanyById(companyId);
    }
}
