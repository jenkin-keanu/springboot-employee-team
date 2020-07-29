package com.thoughtworks.springbootemployee.repository;

import com.thoughtworks.springbootemployee.entity.Company;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CompanyRepository {

    public List<Company> findAll() {
        return new ArrayList<>();
    }

    public Company findCompanyById(int companyId) {
        return new Company();
    }
}
