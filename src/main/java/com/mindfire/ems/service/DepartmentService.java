package com.mindfire.ems.service;

import java.util.List;

import com.mindfire.ems.dto.DepartmentRequestDto;
import com.mindfire.ems.dto.DepartmentResponseDto;

/**
 * Service interface for managing department operations.
 */
public interface DepartmentService {
    /**
     * Adds a new department to the system.
     *
     * @param dto the {@link DepartmentRequestDto} containing the details of the
     *            department to be added
     * @return a {@link DepartmentResponseDto} representing the newly created
     *         department
     */
    DepartmentResponseDto addDepartment(DepartmentRequestDto dto);

    /**
     * Updates an existing department in the system.
     *
     * @param id  the ID of the department to be updated
     * @param dto the {@link DepartmentRequestDto} containing the updated department
     *            details
     * @return a {@link DepartmentResponseDto} representing the updated department
     */
    DepartmentResponseDto updateDepartment(int id, DepartmentRequestDto dto);

    /**
     * Deletes a department by its ID.
     *
     * @param id the ID of the department to be deleted
     */
    void deleteDepartment(int id);

    /**
     * Retrieves a list of all departments in the system.
     *
     * @return a list of {@link DepartmentResponseDto} representing all departments
     */
    List<DepartmentResponseDto> getDepartments();

    /**
     * Retrieves a specific department by its ID.
     *
     * @param id the ID of the department to retrieve
     * @return a {@link DepartmentResponseDto} representing the department with the
     *         given ID
     */
    DepartmentResponseDto getDepartment(int id);

    /**
     * Retrieves a list of employees per department.
     * This is a custom query that returns an array of objects, where each entry
     * contains the department
     * and the associated employee count.
     *
     * @return a list of Object[] where each array represents a department
     *         and the corresponding employee count
     */
    List<Object[]> employeePerDepartment();
}
