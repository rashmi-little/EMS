package com.mindfire.ems.service;

import java.util.List;

import com.mindfire.ems.dto.DepartmentResponseDto;
import com.mindfire.ems.dto.EmployeeResponseDto;
import com.mindfire.ems.dto.EmployeeWithDepartmentDto;

/**
 * Service interface for managing employee-department relationships.
 */
public interface RelationShipService {
    /**
     * Adds an employee to a department.
     *
     * @param empId  the ID of the employee to be added
     * @param deptId the ID of the department to which the employee will be added
     * @return {@code true} if the employee was successfully added to the
     *         department, otherwise {@code false} if employee already exists in the
     *         department
     */
    boolean addEmployeeToDepartment(int empId, int deptId);

    /**
     * Retrieves a list of all employees in a specific department.
     *
     * @param deptId the ID of the department
     * @return a list of {@link EmployeeResponseDto} representing the employees in
     *         the specified department
     */
    List<EmployeeResponseDto> getAllEmployee(int deptId);

    /**
     * Retrieves a list of all departments associated with a specific employee.
     *
     * @param empId the ID of the employee
     * @return a list of {@link DepartmentResponseDto} representing the departments
     *         associated with the employee
     */
    List<DepartmentResponseDto> getAllAssociatedDepartments(int empId);

    /**
     * Removes an employee from a department.
     *
     * @param empId  the ID of the employee to be removed
     * @param deptId the ID of the department from which the employee will be
     *               removed
     * @return {@code true} if the employee was successfully removed from the
     *         department, otherwise {@code false} if the employee not present in
     *         the department
     */
    boolean removeEmployeeFromDepartment(int empId, int deptId);

    /**
     * Transfers an employee from one department to another.
     *
     * @param empId      the ID of the employee to be transferred
     * @param fromDeptId the ID of the department from which the employee is being
     *                   transferred
     * @param toDeptId   the ID of the department to which the employee will be
     *                   transferred
     */
    void transfer(int empId, int fromDeptId, int toDeptId);

    /**
     * Retrieves the employee details along with all associated departments.
     *
     * @param empId the ID of the employee
     * @return an {@link EmployeeWithDepartmentDto} representing the employee and
     *         their associated departments
     */
    EmployeeWithDepartmentDto getAllEmployeeWithDepartments(int empId);

    /**
     * Retrieves a list of all employees along with their associated departments.
     *
     * @return a list of {@link EmployeeWithDepartmentDto} representing all
     *         employees with their departments
     */
    List<EmployeeWithDepartmentDto> getAllEmployeesWithDepartments();
}
