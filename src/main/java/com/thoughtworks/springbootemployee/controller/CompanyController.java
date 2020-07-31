package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.exception.UnknownCompanyException;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping(value = "/companies", params = {"page", "size"})
    public Page<Company> findCompaniesByPage(@PageableDefault() Pageable pageable) {
        return companyService.getPagedCompanies(pageable);
    }

    @GetMapping(value = "/companies")
    public List<Company> findAllCompanies() {
        return companyService.findAllCompanies();
    }

    @GetMapping("/companies/{companyId}/employees")
    public List<Employee> findAllEmployeesInCompany(@PathVariable int companyId) {
        return companyService.findAllEmployeesInCompany(companyId);
    }

    @PostMapping("/companies")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCompany(@RequestBody Company company) {
        companyService.addCompany(company);
    }

    //TODO
    @GetMapping("/companies/{companyId}")
    public Optional<Company> findCompanyById(@PathVariable int companyId) {
        if (!companyService.findCompanyById(companyId).isPresent())
            throw new UnknownCompanyException(companyId,"Find company by id failed! Not found!");
        return companyService.findCompanyById(companyId);

    }

    @PutMapping("/companies/{companyId}")
    public Company updateEmployee(@PathVariable int companyId, @RequestBody Company company) {
        if (!companyService.findCompanyById(companyId).isPresent())
            throw new UnknownCompanyException(companyId,"Update company by id failed! Not found!");
        return companyService.updateCompany(companyId, company);
    }

    @DeleteMapping("/companies/{companyId}")
    public void deleteEmployeeById(@PathVariable int companyId) {
        if(!companyService.findCompanyById(companyId).isPresent())
            throw new UnknownCompanyException(companyId,"Delete company by id failed! Not found!");
        companyService.deleteCompanyById(companyId);
    }
}
