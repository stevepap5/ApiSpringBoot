package com.example.API.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class EmployeeController {

    @Autowired
    EmployeeRepository employeeRepository;

    // Get All Notes
    @GetMapping("/employee")
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    // Create a new Note
    @PostMapping("/employee")
    public Employee createEmployee(@Validated @RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    // Get a Single Note
    @GetMapping("/employee/{id}")
    public Employee getEmployeeById(@PathVariable(value = "id") Long skillsId) throws EmployeeNotFoundException {
        return employeeRepository.findById(skillsId)
                .orElseThrow(() -> new EmployeeNotFoundException(skillsId));
    }

    // Update a Note
    @PutMapping("/employee/{id}")
    public Employee updateEmployee(@PathVariable(value = "id") Long employeeId,
                             @RequestBody Employee employeeDetails) throws EmployeeNotFoundException {

        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException(employeeId));

        employee.setEmployeeName(employeeDetails.getEmployeeName());
        employee.setEmployeeHiringDate(employeeDetails.getEmployeeHiringDate());
        employee.setEmployeeSurname(employeeDetails.getEmployeeSurname());
        employee.setEmployeeSkills(employeeDetails.getEmployeeSkills());

        Employee updatedEmployee = employeeRepository.save(employee);

        return updatedEmployee;
    }

    // Delete a Note
    @DeleteMapping("/employee/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable(value = "id") Long employeeId) throws EmployeeNotFoundException {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException(employeeId));

        employeeRepository.delete(employee);

        return ResponseEntity.ok().build();
    }
}
