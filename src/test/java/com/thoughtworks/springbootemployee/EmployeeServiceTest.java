package com.thoughtworks.springbootemployee;

import com.thoughtworks.springbootemployee.dto.EmployeeRequestDto;
import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import com.thoughtworks.springbootemployee.service.EmployeeServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private CompanyRepository companyRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    /**
     * given
     * employeeRequestDto
     * <p>
     * when
     * add employee
     * <p>
     * then
     * return new employee
     */
//    @Test
//    void should_return_new_employee_when_add_employee_given_new_employee() {
//        //given
//        int companyId = 1;
//        String employeeName = "lebron";
//        int age = 18;
//        String gender = "male";
//
//        EmployeeRequestDto employeeRequest = new EmployeeRequestDto(18, employeeName, gender, companyId);
//        Company company = new Company();
//        company.setCompanyId(companyId);
//        Mockito.when(companyRepository.findById(companyId)).thenReturn(java.util.Optional.of(company));
//
//        Employee employee = new Employee(age, employeeName, gender, company);
//        Mockito.when(employeeRepository.save(any())).thenReturn(employee);
//
//        //when
//        Employee employeeSaved = employeeService.addEmployee(employeeRequest);
//
//        //then
//        Assertions.assertEquals(employeeName,employeeSaved.getName());
//    }
}
