package com.mindfire.ems.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindfire.ems.dto.EmployeeRequestDto;
import com.mindfire.ems.dto.EmployeeResponseDto;
import com.mindfire.ems.dto.PagingResult;
import com.mindfire.ems.service.EmployeeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Tag(name = "Employee APIs", description = "Employee related operation")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Operation(summary = "Create a new employee", description = "This endpoint allows you to create a new employee in the system.")
    @PostMapping("/employees")
    public ResponseEntity<EmployeeResponseDto> createEmployee(@Valid @RequestBody EmployeeRequestDto dto) {
        EmployeeResponseDto savedEmployee = employeeService.addEmployee(dto);

        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @Operation(summary = "Get all employees", description = "This endpoint fetches a list of all employees in the system.")
    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeResponseDto>> getAll() {
        List<EmployeeResponseDto> employees = employeeService.getEmployees();

        return ResponseEntity.ok(employees);
    }

    @Operation(summary = "Get employee by ID", description = "This endpoint fetches the employee details by their unique ID.")
    @GetMapping("/employees/{id}")
    public ResponseEntity<EmployeeResponseDto> getAll(@PathVariable int id) {
        EmployeeResponseDto employee = employeeService.getEmployee(id);

        return ResponseEntity.ok(employee);
    }

    @Operation(summary = "Remove employee by ID", description = "This endpoint removes an employee from the system based on the provided ID.")
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Void> removeEmployee(@PathVariable int id) {
        boolean result = employeeService.deleteEmployee(id);

        return result ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Update employee salary", description = "This endpoint updates the salary of an employee by the provided amount.")
    @PatchMapping("/employees/{id}/salary/{amount}")
    public ResponseEntity<EmployeeResponseDto> updateEmployeeSalary(@PathVariable int id, @PathVariable double amount) {
        EmployeeResponseDto updatedEmployee = employeeService.updateEmployeeSalary(id, amount);

        return ResponseEntity.ok(updatedEmployee);
    }

    @Operation(summary = "Get employees with salary greater than specified amount", description = "This endpoint fetches all employees whose salary is greater than the specified amount.")
    @GetMapping("/employees/salary-greater-than/{amount}")
    public ResponseEntity<List<EmployeeResponseDto>> getEmployeesSalaryGreaterThan(@PathVariable double amount) {
        List<EmployeeResponseDto> employees = employeeService.getEmployeeWithSalaryGreaterThan(amount);

        return ResponseEntity.ok(employees);
    }

    @Operation(summary = "Get employees who joined in the last six months", description = "This endpoint fetches employees who joined the organization in the last six months.")
    @GetMapping("/employees/joined-in-last-six-months")
    public ResponseEntity<List<EmployeeResponseDto>> joinedInPastSixMonths() {
        List<EmployeeResponseDto> employees = employeeService.getAllEmployeeJoinedInlastSixMonth();

        return ResponseEntity.ok(employees);
    }

    @Operation(summary = "Get high paying employees", description = "This endpoint fetches employees who are earning more than the third-highest salary.")
    @GetMapping("/employees/high-paying")
    public ResponseEntity<List<EmployeeResponseDto>> highPayingEmployee() {
        List<EmployeeResponseDto> response = employeeService.earningMorethanThirdHighestSalary();

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Bulk update employee salary", description = "This endpoint allows you to update the salary of all employees by a specified percentage increase.")
    @PutMapping("/employees/bonus/{incrementPercentage}")
    public ResponseEntity<Void> bulkSalaryUpdate(@PathVariable double incrementPercentage) {
        employeeService.bulkSalaryUpdate(incrementPercentage);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(summary = "Get page of employees in batch", description = "This endpoint fetches employees in a paginated manner, sorted by salary in descending order.")
    @GetMapping("/employees/batch/sort/{pageNumber}")
    public ResponseEntity<PagingResult<EmployeeResponseDto>> getEmployeesInBatchOrderBySalary(
            @PathVariable int pageNumber) {
        PagingResult<EmployeeResponseDto> pageEmployee = employeeService
                .getEmployeeInBatchSortBySalaryInDesc(pageNumber);

        return ResponseEntity.ok(pageEmployee);
    }

    @Operation(summary = "Update employee details", description = "This endpoint allows you to update the details of an employee, including fields such as name, position, department, etc.")
    @PutMapping("/employees/{id}")
    public ResponseEntity<EmployeeResponseDto> updateEmployee(@PathVariable int id,
            @Valid @RequestBody EmployeeRequestDto dto) {
        EmployeeResponseDto updatedEmployee = employeeService.updateEmployee(id, dto);
        System.out.println(updatedEmployee);
        return updatedEmployee != null ? ResponseEntity.ok(updatedEmployee) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Get page of employees in batch", description = "This endpoint fetches employees in a paginated manner in batch of 5")
    @GetMapping("/employees/batch/{pageNumber}")
    public ResponseEntity<PagingResult<EmployeeResponseDto>> getEmployeesInBatch(@PathVariable int pageNumber) {
        PagingResult<EmployeeResponseDto> pageEmployee = employeeService
                .getEmployeeInBatch(pageNumber);

        return ResponseEntity.ok(pageEmployee);
    }
}
