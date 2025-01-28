package com.mindfire.ems.service;

import java.util.List;

import com.mindfire.ems.model.Employee;

public interface EmployeeService {
    Employee addEmployee(Employee employee);

    Employee updateEmployeeSalary(int id, double updatedSalary);

    void deleteEmployee(int id);

    List<Employee> getEmployees();

    Employee getEmployee(int id);
}
