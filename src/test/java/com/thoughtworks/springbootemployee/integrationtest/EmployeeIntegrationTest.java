package com.thoughtworks.springbootemployee.integrationtest;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.service.CompanyService;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
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
        //when(companyRepository.findById(1)).thenReturn(java.util.Optional.of(company));

        Employee employee = new Employee(18,"jenkin","male",company);
        Employee savedEmployee=employeeRepository.save(employee);
        mockMvc.perform(MockMvcRequestBuilders.
                get("/employees/"+savedEmployee.getId())).
                andExpect(status().isOk()).andExpect(jsonPath("name").value("jenkin"));
    }
}
