package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @GetMapping("/companies")
    public List<Company> findAllCompanies(){
        return companyService.findAllCompanies();
    }
    @GetMapping("/companies")
    public List<Company> findCompaniesByCondition(@RequestParam(required = false, defaultValue = "-1") int page,
                                                   @RequestParam(required = false, defaultValue = "-1") int pageSize) {
        if (page != -1) return getPagedCompanies(page, pageSize);
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

    private List<Company> getPagedCompanies(int page, int pageSize) {
        List<Company> companies = companyService.findAllCompanies();
        List<Company> pagedCompanies = new ArrayList<>();

        int startRow = (page - 1) * pageSize;
        int endRow = startRow + pageSize;
        for (int index = startRow; index < companies.size() && index < endRow; index++) {
            pagedCompanies.add(companies.get(index));
        }
        return pagedCompanies;
    }
}
