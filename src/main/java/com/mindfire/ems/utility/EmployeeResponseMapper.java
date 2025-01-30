package com.mindfire.ems.utility;

import com.mindfire.ems.dto.EmployeeResponseDto;
import com.mindfire.ems.model.Employee;

/**
 * A utility class for mapping {@link Employee} entities to
 * {@link EmployeeResponseDto} data transfer objects (DTOs).
 */
public class EmployeeResponseMapper {
    /**
     * Converts an {@link Employee} entity to an {@link EmployeeResponseDto}.
     *
     * @param employee the {@link Employee} entity to be converted
     * @return an {@link EmployeeResponseDto} containing the employee's ID, name,
     *         email, salary, and date of joining
     */
    public static EmployeeResponseDto convertEmployeeResponseDto(Employee employee) {
        EmployeeResponseDto dto = new EmployeeResponseDto(employee.getId(), employee.getName(), employee.getEmail(),
                employee.getSalary(), employee.getDateOfJoining());

        return dto;
    }
}
