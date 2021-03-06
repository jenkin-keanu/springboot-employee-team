package com.thoughtworks.springbootemployee.integrationtest;

import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.exception.UnknownEmployeeException;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.junit.Rule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.util.NestedServletException;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

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
                .andExpect(jsonPath("name").value("Jenkin"))
                .andExpect(jsonPath("age").value(14));
    }


    @Test
    void should_return_a_new_company_when_update_company_given_a_company() throws Exception {
        Company company = new Company();
        company.setName("jenkin");
        Company savedCompany=companyRepository.save(company);
        Employee employee=new Employee(1,"hhh","mele",savedCompany);
        Employee savedEmployee=employeeRepository.save(employee);

        String employeeJson="{\n" +
                "        \"name\": \"Jenkin\",\n" +
                "        \"age\": 14,\n" +
                "        \"gender\": \"male\",\n" +
                "        \"companyId\": "+company.getId()+"\n" +
                "}";
        mockMvc.perform(put("/employees/"+savedEmployee.getId()).
                contentType(MediaType.APPLICATION_JSON).
                content(employeeJson)).
                andExpect(status().isOk()).andExpect(jsonPath("name").value("Jenkin"));
    }

    @Test
    void should_return_null_when_delete_employee_given_an_id() throws Exception {
        Company company = new Company();
        company.setName("jenkin");
        Company savedCompany=companyRepository.save(company);
        Employee employee=new Employee(88,"Keanu","male",savedCompany);
        Employee savedEmployee=employeeRepository.save(employee);

        mockMvc.perform(delete("/employees/"+savedEmployee.getId())).andExpect(status().isOk());
        Optional<Employee> employeeQuery=employeeRepository.findById(savedEmployee.getId());
        Assertions.assertFalse(employeeQuery.isPresent());
    }

    @Test
    void should_return_8_employees_when_find_all_employees_given_8_employees() throws Exception {
        Company company = new Company();
        company.setName("Keanu");
        Company savedCompany=companyRepository.save(company);
        for(int i=0;i<8;i++){
            Employee employee = new Employee(23+i,"jenkin"+i,"male",savedCompany);
            employeeRepository.save(employee);
        }
        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(8));
    }

    @Test
    void should_return_paged_employees_when_find_employees_by_page_given_page_and_size() throws Exception {
        Company company = new Company();
        company.setName("Keanu");
        Company savedCompany=companyRepository.save(company);
        for(int i=0;i<8;i++){
            Employee employee = new Employee(23+i,"jenkin"+i,"male",savedCompany);
            employeeRepository.save(employee);
        }
        int page = 2;
        int size = 3;
        mockMvc.perform(get("/employees")
                .param("page", String.valueOf(page))
                .param("size", String.valueOf(size)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(2))
                .andExpect(jsonPath("content[0].name").value("jenkin6"))
                .andExpect(jsonPath("content[1].name").value("jenkin7"));
    }

    @Test()
    void should_return_not_found_when_find_a_not_exist_employee_given_an_employee_id() throws Exception {
        mockMvc.perform(get("/employees/1")).andExpect(status().isNotFound());
    }

    @Test
    void should_return_not_found_when_delete_a_not_exist_employee_given_an_employee_id() throws Exception {
        mockMvc.perform(delete("/employees/1")).andExpect(status().isNotFound());
    }

    @Test
    void should_return_not_found_when_update_a_not_exist_employee_given_a_employee_id() throws Exception {
        String employeeJson="{\n" +
                "        \"name\": \"Jenkin\",\n" +
                "        \"age\": 14,\n" +
                "        \"gender\": \"male\",\n" +
                "        \"companyId\": "+1+"\n" +
                "}";
        mockMvc.perform(put("/employees/1").
                contentType(MediaType.APPLICATION_JSON).
                content(employeeJson)).
                andExpect(status().isNotFound());
    }
}
