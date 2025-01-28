package com.mindfire.ems.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mindfire.ems.model.Employee;
import com.mindfire.ems.repository.EmployeeRepository;
import com.mindfire.ems.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public Employee addEmployee(Employee employee) {
       return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployeeSalary(int id, double updatedSalary) {
       Employee employee = getEmployee(id);
       employee.setSalary(updatedSalary);
       return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(int id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee getEmployee(int id) {
        return employeeRepository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
    }

}
