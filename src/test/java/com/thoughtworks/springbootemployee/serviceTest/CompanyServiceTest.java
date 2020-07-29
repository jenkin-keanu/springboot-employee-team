package com.thoughtworks.springbootemployee.serviceTest;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.tddservice.TddCompanyService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class CompanyServiceTest {
    @Mock
    private CompanyRepository companyRepository;
    @Mock
    private EmployeeRepository employeeRepository;
    @InjectMocks
    private TddCompanyService tddCompanyService;

    /**
     * given
     * 2 companies
     * when
     * find all companies
     * then
     * return all companies
     */
    @Test
    void should_return_all_companies_when_find_all_companies_given_none(){
        //given
        List<Company> companies = new ArrayList<>();
        for(int i=0;i<2;i++){
            Company company = new Company();
            companies.add(company);
        }
        Mockito.when(companyRepository.findAll()).thenReturn(companies);
        //when
        List<Company> companiesQuery = tddCompanyService.findAll();

        //then
        Assertions.assertEquals(2,companiesQuery.size());
    }
}
