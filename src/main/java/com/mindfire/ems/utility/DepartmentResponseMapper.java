package com.mindfire.ems.utility;

import com.mindfire.ems.dto.DepartmentResponseDto;
import com.mindfire.ems.model.Department;

public class DepartmentResponseMapper {
    public static DepartmentResponseDto convertDepartmentResponseDto(Department department) {
        DepartmentResponseDto dto = new DepartmentResponseDto(department.getId(), department.getName(),
                department.getLocation());
        return dto;
    }
}
