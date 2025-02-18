package com.mindfire.ems.service;

import java.util.List;

import com.mindfire.ems.dto.EmployeeRequestDto;
import com.mindfire.ems.dto.EmployeeResponseDto;

/**
 * Service interface for managing employee operations.
 */
public interface EmployeeService {
    /**
     * Adds a new employee to the system.
     *
     * @param dto the {@link EmployeeRequestDto} containing the details of the
     *            employee to be added
     * @return a {@link EmployeeResponseDto} representing the newly created employee
     */
    EmployeeResponseDto addEmployee(EmployeeRequestDto dto);

    /**
     * Updates the salary of an existing employee.
     *
     * @param id            the ID of the employee whose salary needs to be updated
     * @param updatedSalary the new salary to be set
     * @return an updated {@link EmployeeResponseDto} representing the employee with
     *         the new salary
     */
    EmployeeResponseDto updateEmployeeSalary(int id, double updatedSalary);

    /**
     * Deletes an employee by their ID.
     *
     * @param id the ID of the employee to be deleted
     */
    boolean deleteEmployee(int id);

    /**
     * Retrieves a list of all employees in the system.
     *
     * @return a list of {@link EmployeeResponseDto} representing all employees
     */
    List<EmployeeResponseDto> getEmployees();

    /**
     * Retrieves a specific employee by their ID.
     *
     * @param id the ID of the employee to retrieve
     * @return an {@link EmployeeResponseDto} representing the employee with the
     *         given ID
     */
    EmployeeResponseDto getEmployee(int id);

    /**
     * Retrieves a list of employees who have a salary greater than the given
     * amount.
     *
     * @param amount the salary threshold
     * @return a list of {@link EmployeeResponseDto} representing employees with
     *         salary greater than the given amount
     */
    List<EmployeeResponseDto> getEmployeeWithSalaryGreaterThan(double amount);

    /**
     * Retrieves a list of employees who joined in the last six months.
     *
     * @return a list of {@link EmployeeResponseDto} representing employees who
     *         joined in the last six months
     */
    List<EmployeeResponseDto> getAllEmployeeJoinedInlastSixMonth();

    /**
     * Retrieves a list of employees who are earning more than or equal to the third
     * highest salary in the company.
     *
     * @return a list of {@link EmployeeResponseDto} representing employees earning
     *         more than or equal the third highest salary
     */
    List<EmployeeResponseDto> earningMorethanThirdHighestSalary();

    /**
     * Performs a bulk salary update by a percentage for all employees.
     *
     * @param percentage the percentage by which to increase the salary
     */
    void bulkSalaryUpdate(double percentage);

    /**
     * Retrieves a paginated and sorted list of employees, sorted by salary in
     * descending order in a batch of 5.
     *
     * @param pageNumber the page number for pagination
     * @return a list of {@link EmployeeResponseDto} representing employees, sorted
     *         by salary in descending order
     */
    List<EmployeeResponseDto> getEmployeeInBatchSortBySalaryInDesc(int pageNumber);

    /**
     * helps to update employee details
     * 
     * @param id  of the employee
     * @param dto update employee details
     * @return an{@link EmployeeResponseDto} representing employee with updated
     *         field value
     */
    EmployeeResponseDto updateEmployee(int id, EmployeeRequestDto dto);
}
