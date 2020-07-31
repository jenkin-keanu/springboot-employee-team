package com.thoughtworks.springbootemployee.integrationtest;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private EmployeeService employeeService;

    @BeforeEach
    void prepareData(){

    }

    @AfterEach
    void tearDown(){
        employeeRepository.deleteAll();
    }

    @Test
    void should_return_ok_when_find_employee_by_id_given_a_valid_id() throws Exception {
        Company company = new Company("keanu");
        company = companyRepository.save(company);
        Employee employee = new Employee(18,"jenkin","male",company);
        Employee savedEmployee=employeeRepository.save(employee);
        mockMvc.perform(MockMvcRequestBuilders.
                get("/employees/"+savedEmployee.getId())).
                andExpect(status().isOk()).andExpect(jsonPath("name").value("jenkin"));
    }

    @Test
    void should_return_is_created_when_save_an_employee_given_an_employee() throws Exception {
        Company company = new Company("Keanu");
        company = companyRepository.save(company);
        String employeeJson="{\n" +
                "        \"name\": \"Jenkin\",\n" +
                "        \"age\": 14,\n" +
                "        \"gender\": \"male\",\n" +
                "        \"companyId\": "+company.getId()+"\n" +
                "}";
        mockMvc.perform(post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(employeeJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("gender").value("male"));
    }
}
