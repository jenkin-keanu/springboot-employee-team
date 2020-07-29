package com.thoughtworks.springbootemployee.controller;

import com.thoughtworks.springbootemployee.dto.EmployeeRequestDto;
import com.thoughtworks.springbootemployee.entity.Employee;
import com.thoughtworks.springbootemployee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees/{employeeId}")
    public Optional<Employee> findEmployeeById(@PathVariable int employeeId) {
        return employeeService.findEmployeeById(employeeId);
    }

    @GetMapping(value = "/employees",params = {"page","size"})
    public Page<Employee> findEmployeesByPage(@PageableDefault Pageable pageable) {
        return employeeService.getPagedEmployees(pageable);
    }

    @GetMapping(value = "/employees",params = {"gender"})
    public List<Employee> findEmployeesByGender(@RequestParam String gender) {
        return employeeService.findEmployeesByGender(gender);
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
    public void addEmployee(@RequestBody EmployeeRequestDto employeeRequestDto) {
        employeeService.addEmployee(employeeRequestDto);
    }

    @PutMapping("/employees/{employeeId}")
    public Employee updateEmployee(@PathVariable int employeeId, @RequestBody EmployeeRequestDto employeeRequestDto) {
        return employeeService.updateEmployee(employeeId, employeeRequestDto);
    }

    @DeleteMapping("/employees/{employeeId}")
    public void deleteEmployeeById(@PathVariable int employeeId){
        employeeService.deleteEmployeeById(employeeId);
    }
}
