package com.mindfire.ems.dto;

import java.util.List;

public record EmployeeWithDepartmentDto(EmployeeResponseDto employee, List<DepartmentResponseDto> departments) {

}
