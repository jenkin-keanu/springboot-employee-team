package com.thoughtworks.springbootemployee.service;


import com.thoughtworks.springbootemployee.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> findAllEmployees();

    Employee findEmployeeById(int employeeId);

    void addEmployee(Employee employee);

    Employee updateEmployee(int employeeId, Employee employee);

    void deleteEmployeeById(int employeeId);
}
