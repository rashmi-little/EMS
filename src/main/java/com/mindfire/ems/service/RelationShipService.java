package com.mindfire.ems.service;

import java.util.List;

import com.mindfire.ems.dto.DepartmentResponseDto;
import com.mindfire.ems.dto.EmployeeResponseDto;
import com.mindfire.ems.dto.EmployeeWithDepartmentDto;

public interface RelationShipService {
    boolean addEmployeeToDepartment(int empId, int deptId);

    List<EmployeeResponseDto> getAllEmployee(int deptId);

    List<DepartmentResponseDto> getAllAssociatedDepartments(int empId);

    boolean removeEmployeeFromDepartment(int empId, int deptId);

    void transfer(int empId, int fromDeptId, int toDeptId);

    EmployeeWithDepartmentDto getAllEmployeeWithDepartments(int empId);

    List<EmployeeWithDepartmentDto> getAllEmployeesWithDepartments();
}
