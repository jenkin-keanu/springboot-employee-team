package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> findAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public List<Employee> findAllEmployeesInCompany(int companyId) {
        Company company = companyRepository
                .findAll()
                .stream()
                .filter(e -> e.getCompanyId() == companyId)
                .findFirst()
                .orElse(null);

        return company == null ? null : company.getEmployees();
    }

    @Override
    public void addCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public Optional<Company> findCompanyById(int companyId) {
        return companyRepository.findById(companyId);
    }

    @Override
    public Company updateCompany(int companyId, Company company) {
        if (companyRepository.findById(companyId).equals(null))
            return null;
        return companyRepository.save(company);
    }

    @Override
    public Company deleteCompanyById(int companyId) {
        Company company = companyRepository.findById(companyId).get();
        if (company.equals(null))
            return null;
        companyRepository.deleteById(companyId);
        return company;
    }

    @Override
    public Page<Company> getPagedCompanies(Pageable pageable) {
        return companyRepository.findAll(pageable);
    }

}
