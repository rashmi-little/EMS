package com.mindfire.ems.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mindfire.ems.model.Department;
import com.mindfire.ems.model.Employee;
import com.mindfire.ems.repository.DepartmentRepository;
import com.mindfire.ems.repository.EmployeeRepository;
import com.mindfire.ems.service.RelationShipService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RelationShipServiceImpl implements RelationShipService {
    private final EmployeeRepository employeeRepository;

    private final DepartmentRepository departmentRepository;

    @Override
    public boolean addEmployeeToDepartment(int empId, int deptId) {
        Department department = departmentRepository.findById(deptId).orElseThrow();
        Employee employee = employeeRepository.findById(empId).orElseThrow();

        if (department.getEmployees().contains(employee)) {
            return false;
        }
        List<Employee> currentEmployees = department.getEmployees();
        currentEmployees.add(employee);
        department.setEmployees(currentEmployees);

        departmentRepository.save(department);
        return true;
    }

}
