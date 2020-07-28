package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService{

    private List<Company> companies = new ArrayList<>();

    @Override
    public List<Company> findAllCompanies() {
        return companies;
    }
    @Override
    public List<Employee> findAllEmployeesInCompany(int companyId) {
        for (Company company:companies ) {
            if (company.getCompanyId() == companyId)
                return company.getEmployees();
        }
        return null;
    }
    @Override
    public void addCompany(Company company) {
        companies.add(company);
    }

    @Override
    public Company findCompanyById(int companyId) {
        for ( Company company: companies) {
            if (company.getCompanyId() == companyId)
                return company;
        }
        return null;
    }

    @Override
    public Company updateCompany(int companyId, Company company) {
        for(Company item:companies){
            if (item.getCompanyId() == companyId){
                item.setCompanyId(company.getCompanyId());
                item.setEmployees(company.getEmployees());
                return item;
            }
        }
        return null;
    }
}
