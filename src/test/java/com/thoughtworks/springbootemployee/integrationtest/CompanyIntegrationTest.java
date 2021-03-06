package com.thoughtworks.springbootemployee.integrationtest;


import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.service.CompanyService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
    private EmployeeRepository employeeRepository;

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
                get("/companies/"+savedCompany.getId())).
                andExpect(status().isOk()).andExpect(jsonPath("name").value("jenkin"));
    }

    @Test
    void should_return_is_created_when_save_a_company_given_a_company() throws Exception {
        String companyJson="{\n" +
                "\t\"name\":\"cargoSmart\"\n" +
                "}";
        mockMvc.perform(post("/companies").contentType(MediaType.APPLICATION_JSON).content(companyJson)).andExpect(status().isCreated());
        List<Company> companies=companyRepository.findByName("cargoSmart");
        Assertions.assertEquals(1,companies.size());
    }

    @Test
    void should_return_a_new_company_when_update_company_given_a_company() throws Exception {
        Company company = new Company();
        company.setName("jenkin");
        Company savedCompany=companyRepository.save(company);

        String companyJson = "{\n" +
                "\t\"name\":\"keanu\"\n" +
                "}";
        mockMvc.perform(put("/companies/"+savedCompany.getId()).
                contentType(MediaType.APPLICATION_JSON).
                content(companyJson)).
                andExpect(status().isOk()).andExpect(jsonPath("name").value("keanu"));
    }

    @Test
    void should_return_null_when_delete_company_given_an_id() throws Exception {
        Company company = new Company();
        company.setName("jenkin");
        Company savedCompany=companyRepository.save(company);

        mockMvc.perform(delete("/companies/"+savedCompany.getId())).andExpect(status().isOk());
        Optional<Company> companyQuery=companyRepository.findById(savedCompany.getId());
        Assertions.assertFalse(companyQuery.isPresent());
    }

    @Test
    void should_return_6_companies_when_find_all_companies_given_6_companies() throws Exception {
        for(int i=0;i<6;i++){
            Company company = new Company();
            company.setName("jenkin"+i);
            companyRepository.save(company);
        }
        mockMvc.perform(get("/companies")).andExpect(status().isOk());
        List<Company> companies=companyRepository.findAll();
        Assertions.assertEquals(6,companies.size());
    }

    @Test
    void should_return_paged_companies_when_find_companies_by_page_given_page_and_size() throws Exception {
        for(int i=0;i<6;i++){
            Company company = new Company();
            company.setName("jenkin"+i);
            companyRepository.save(company);
        }
        int page = 1;
        int size = 3;
        mockMvc.perform(get("/companies")
                .param("page", String.valueOf(page))
                .param("size", String.valueOf(size)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(3))
                .andExpect(jsonPath("content[0].name").value("jenkin3"))
                .andExpect(jsonPath("content[1].name").value("jenkin4"))
                .andExpect(jsonPath("content[2].name").value("jenkin5"));
    }

    @Test
    void should_return_not_found_when_find_a_not_exist_company_given_a_company_id() throws Exception {
        mockMvc.perform(get("/companies/1")).andExpect(status().isNotFound());
    }

    @Test
    void should_return_not_found_when_delete_a_not_exist_company_given_a_company_id() throws Exception {
        mockMvc.perform(delete("/companies/1")).andExpect(status().isNotFound());
    }

    @Test
    void should_return_not_found_when_update_a_not_exist_company_given_a_company_id() throws Exception {
        String companyJson = "{\n" +
                "\t\"name\":\"keanu\"\n" +
                "}";
        mockMvc.perform(put("/companies/1").
                contentType(MediaType.APPLICATION_JSON).
                content(companyJson)).
                andExpect(status().isNotFound());
    }

    @Test
    void should_return_6_employees_when_find_all_employees_in_a_company_given_a_company_with_6_employees() throws Exception {
        Company company = new Company();
        company.setName("Keanu");
        Company savedCompany=companyRepository.save(company);
        for(int i=0;i<6;i++){
            Employee employee = new Employee(23+i,"jenkin"+i,"male",savedCompany);
            employeeRepository.save(employee);
        }
        mockMvc.perform(get("/companies/"+savedCompany.getId()+"/employees"))
                .andExpect(status().isOk()).andExpect(jsonPath("$.length()").value(6));
    }
}
