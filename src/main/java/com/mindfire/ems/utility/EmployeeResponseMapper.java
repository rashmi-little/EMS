package com.mindfire.ems.utility;


import com.mindfire.ems.dto.EmployeeResponseDto;
import com.mindfire.ems.model.Employee;

public class EmployeeResponseMapper {
    public static EmployeeResponseDto convertEmployeeResponseDto(Employee employee) {
        EmployeeResponseDto dto = new EmployeeResponseDto(employee.getId(), employee.getName(), employee.getEmail(),
                employee.getSalary(), employee.getDateOfJoining());

        return dto;
    }
}
