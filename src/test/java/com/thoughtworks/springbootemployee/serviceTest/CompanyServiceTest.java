package com.thoughtworks.springbootemployee.serviceTest;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
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
    void should_return_all_companies_when_find_all_companies_given_2_companies(){
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
    /**
     * GET       /companies/1  #obtain a certain specific company
     * given
     * company id 1
     * 2 companies
     * when
     * find specific company by id
     * then
     * return company with id = 1
     */
    @Test
    void should_return_company_1_when_find_company_by_id_given_company_id_1_and_2_companies(){
        //given
        List<Company> companies = new ArrayList<>();
        for(int i=0;i<2;i++){
            Company company = new Company();
            company.setCompanyId(i);
            companies.add(company);
        }
        int companyId = 1;
        Mockito.when(companyRepository.findCompanyById(companyId)).thenReturn(companies.get(companyId));
        //when

        Company specificCompany = tddCompanyService.findCompanyById(companyId);
        //then
        Assertions.assertEquals(companyId,specificCompany.getCompanyId());
    }

}
