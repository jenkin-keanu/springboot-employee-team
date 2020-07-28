package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import java.util.List;

public interface CompanyService {
    List<Company> findAllCompanies();
    List<Employee> findAllEmployeesInCompany(int companyId);
    void addCompany(Company company);
    Company findCompanyById(int companyId);
    Company updateCompany(int companyId, Company company);
}
