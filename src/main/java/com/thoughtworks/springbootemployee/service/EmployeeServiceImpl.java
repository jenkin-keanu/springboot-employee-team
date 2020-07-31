package com.thoughtworks.springbootemployee.service;

import com.thoughtworks.springbootemployee.dto.EmployeeRequestDto;
import com.thoughtworks.springbootemployee.entity.Company;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.repository.CompanyRepository;
import com.thoughtworks.springbootemployee.repository.EmployeeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final CompanyRepository companyRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, CompanyRepository companyRepository) {
        this.employeeRepository = employeeRepository;
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> findEmployeeById(int employeeId) {
        return employeeRepository.findById(employeeId);
    }

    @Override
    public Employee addEmployee(EmployeeRequestDto employeeRequestDto) {
        Employee employee = new Employee();
        employee = toEmployeeEntity(employeeRequestDto, employee);
        return employeeRepository.save(employee);
    }

    private Employee toEmployeeEntity(EmployeeRequestDto employeeRequestDto, Employee employee) {
        Company company = companyRepository.findById(employeeRequestDto.getCompanyId()).get();
        employee.setCompany(company);
        employee.setName(employeeRequestDto.getName());
        employee.setGender(employeeRequestDto.getGender());
        employee.setAge(employeeRequestDto.getAge());
        return employee;
    }

    @Override
    public Employee updateEmployee(int employeeId, EmployeeRequestDto employeeRequestDto) {
        Employee employee = employeeRepository.findById(employeeId).get();
        toEmployeeEntity(employeeRequestDto, employee);
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployeeById(int employeeId) {
        employeeRepository.deleteById(employeeId);
    }

    @Override
    public Page<Employee> getPagedEmployees(Pageable pageable) {
        return employeeRepository.findAll(pageable);
    }

    //TODO
    @Override
    public List<Employee> findEmployeesByGender(String gender) {
        return employeeRepository.findAll()
                .stream()
                .filter(employee -> employee.getGender().equals(gender))
                .collect(Collectors.toList());
    }
}
