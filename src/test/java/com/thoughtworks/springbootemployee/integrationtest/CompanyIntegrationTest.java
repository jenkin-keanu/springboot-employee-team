package com.thoughtworks.springbootemployee.integrationtest;


import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.JsonPath;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CompanyIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CompanyService companyService;

    @BeforeEach
    void prepareData(){

    }

    @AfterEach
    void tearDown(){
        companyRepository.deleteAll();
    }

    @Test
    void should_return_ok_when_find_company_by_id_given_a_valid_id() throws Exception {
        Company company = new Company();
        company.setName("jenkin");
        Company savedCompany=companyRepository.save(company);
        mockMvc.perform(MockMvcRequestBuilders.
                get("/companies/"+savedCompany.getCompanyId())).
                andExpect(status().isOk()).andExpect(jsonPath("name").value("jenkin"));
    }
}
