package com.thoughtworks.springbootemployee.tddservice;

import com.thoughtworks.springbootemployee.entity.Company;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TddCompanyService {
    public List<Company> findAll() {
        return new ArrayList<>();
    }
}
