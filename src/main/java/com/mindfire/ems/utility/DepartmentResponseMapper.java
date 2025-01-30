package com.mindfire.ems.utility;

import com.mindfire.ems.dto.DepartmentResponseDto;
import com.mindfire.ems.model.Department;

/**
 * A utility class for mapping {@link Department} entities to {@link DepartmentResponseDto} data transfer objects (DTOs).
 */
public class DepartmentResponseMapper {
    /**
     * Converts a {@link Department} entity to a {@link DepartmentResponseDto}.
     *
     * @param department the {@link Department} entity to be converted
     * @return a {@link DepartmentResponseDto} containing the department's ID, name,
     *         and location
     */
    public static DepartmentResponseDto convertDepartmentResponseDto(Department department) {
        DepartmentResponseDto dto = new DepartmentResponseDto(department.getId(), department.getName(),
                department.getLocation());
        return dto;
    }
}
