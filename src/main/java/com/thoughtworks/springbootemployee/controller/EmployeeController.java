package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees/{employeeId}")
    public Employee findEmployeeById(@PathVariable int employeeId) {
        return employeeService.findEmployeeById(employeeId);
    }

    @GetMapping("/employees")
    public List<Employee> findEmployeesByCondition(@RequestParam(required = false, defaultValue = "-1") int page,
                                              @RequestParam(required = false, defaultValue = "-1") int pageSize,
                                              @RequestParam(required = false, defaultValue = "-1") String gender) {
        if (page != -1) return getPagesEmployees(page, pageSize);
        if (!"-1".equals(gender)) return getEmployeesWithGender(gender);
        return employeeService.findAllEmployees();
    }

    private List<Employee> getPagesEmployees(int page, int pageSize) {
        List<Employee> employees = employeeService.findAllEmployees();
        List<Employee> pagedEmployees = new ArrayList<>();

        int startRow = (page - 1) * pageSize;
        int endRow = startRow + pageSize;
        for (int index = startRow; index < employees.size() && index < endRow; index++) {
            pagedEmployees.add(employees.get(index));
        }
        return pagedEmployees;
    }

    private List<Employee> getEmployeesWithGender(String gender) {
        List<Employee> employees = employeeService.findAllEmployees();
        List<Employee> employeesWithGender = new ArrayList<>();

        for (int index = 0; index < employees.size(); index++) {
            if (employees.get(index).getGender().equals(gender))
                employeesWithGender.add(employees.get(index));
        }
        return employeesWithGender;
    }

    @PostMapping("/employees")
    public void addEmployee(@RequestBody Employee employee) {
        employeeService.addEmployee(employee);
    }

    @PutMapping("/employees/{employeeId}")
    public Employee updateEmployee(@PathVariable int employeeId, @RequestBody Employee employee) {
        return employeeService.updateEmployee(employeeId, employee);
    }

    @DeleteMapping("/employees/{employeeId}")
    public void deleteEmployeeById(@PathVariable int employeeId){
        employeeService.deleteEmployeeById(employeeId);
    }
}
