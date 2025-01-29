package com.mindfire.ems.service;

import java.util.List;

import com.mindfire.ems.dto.EmployeeRequestDto;
import com.mindfire.ems.dto.EmployeeResponseDto;

public interface EmployeeService {
    EmployeeResponseDto addEmployee(EmployeeRequestDto dto);

    EmployeeResponseDto updateEmployeeSalary(int id, double updatedSalary);

    void deleteEmployee(int id);

    List<EmployeeResponseDto> getEmployees();

    EmployeeResponseDto getEmployee(int id);

    List<EmployeeResponseDto> getEmployeeWithSalaryGreaterThan(double amount);

    List<EmployeeResponseDto> getAllEmployeeJoinedInlastSixMonth();

    List<EmployeeResponseDto> earningMorethanThirdHighestSalary();
}
