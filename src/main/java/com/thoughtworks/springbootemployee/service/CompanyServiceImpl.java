package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.entity.Company;
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

}
