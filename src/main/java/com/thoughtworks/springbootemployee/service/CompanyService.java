package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CompanyService {
    List<Company> findAllCompanies();

    List<Employee> findAllEmployeesInCompany(int companyId);

    void addCompany(Company company);

    Optional<Company> findCompanyById(int companyId);

    Company updateCompany(int companyId, Company company);

    Company deleteCompanyById(int companyId);

    Page<Company> getPagedCompanies(Pageable pageable);

}
