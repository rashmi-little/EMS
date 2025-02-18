package com.mindfire.ems.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindfire.ems.dto.DepartmentResponseDto;
import com.mindfire.ems.dto.EmployeeResponseDto;
import com.mindfire.ems.dto.EmployeeWithDepartmentDto;
import com.mindfire.ems.service.RelationShipService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@CrossOrigin
@Tag(name = "Employee_Department APIs", description = "Operation related to both employee and department")
public class RelationShipController {

    private final RelationShipService relationShipService;

    @Operation(summary = "Add an employee to a department", description = "This endpoint allows you to assign an employee to a department.")
    @PostMapping("/relationship/employees/{empId}/departments/{deptId}")
    public ResponseEntity<String> addEmployeeToDepartment(@PathVariable int empId, @PathVariable int deptId) {
        boolean status = relationShipService.addEmployeeToDepartment(empId, deptId);

        if (status) {
            return ResponseEntity.ok("Employee added to department successfully");
        }

        return ResponseEntity.status(HttpStatus.CONFLICT).body("Employee already in the department");
    }

    @Operation(summary = "Get all employees by department ID", description = "This endpoint fetches all employees belonging to a specified department.")
    @GetMapping("/relationship/employees/{deptId}")
    public ResponseEntity<List<EmployeeResponseDto>> getAllEmployeesByDepartmentId(@PathVariable int deptId) {
        List<EmployeeResponseDto> employees = relationShipService.getAllEmployee(deptId);

        return ResponseEntity.ok(employees);
    }

    @Operation(summary = "Get all departments associated with an employee", description = "This endpoint retrieves all the departments associated with a given employee.")
    @GetMapping("/relationship/departments/{empId}")
    public ResponseEntity<List<DepartmentResponseDto>> getAllDepartmentsAssociatedWithEmployee(
            @PathVariable int empId) {
        List<DepartmentResponseDto> departments = relationShipService.getAllAssociatedDepartments(empId);

        return ResponseEntity.ok(departments);
    }

    @Operation(summary = "Remove an employee from a department", description = "This endpoint allows you to remove an employee from a department.")
    @DeleteMapping("/relationship/employees/{empId}/departments/{deptId}")
    public ResponseEntity<Void> removeEmployeeFromDepartment(@PathVariable int empId, @PathVariable int deptId) {
        boolean result = relationShipService.removeEmployeeFromDepartment(empId, deptId);

        return result ? ResponseEntity.notFound().build() : ResponseEntity.noContent().build();
    }

    @Operation(summary = "Transfer an employee between departments", description = "This endpoint allows you to transfer an employee from one department to another.")
    @PutMapping("/relationship/transfer/employees/{empId}/from/{fromDeptId}/to/{toDeptId}")
    public ResponseEntity<Void> transferEmployee(@PathVariable int empId, @PathVariable int fromDeptId,
            @PathVariable int toDeptId) {
        relationShipService.transfer(empId, fromDeptId, toDeptId);

        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get all employees with their associated departments", description = "This endpoint fetches all employees along with the departments they are part of.")
    @GetMapping("/relationship/employees")
    public ResponseEntity<List<EmployeeWithDepartmentDto>> getEmployees() {
        List<EmployeeWithDepartmentDto> response = relationShipService.getAllEmployeesWithDepartments();

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get employee with their associated departments", description = "This endpoint retrieves an employee along with the departments they are associated with.")
    @GetMapping("/relationship/employees/{empId}/departments")
    public ResponseEntity<EmployeeWithDepartmentDto> getEmployeeWithDepartments(@PathVariable int empId) {
        EmployeeWithDepartmentDto employeeWithDepartments = relationShipService.getEmployeeWithDepartments(empId);

        return ResponseEntity.ok(employeeWithDepartments);
    }

    @Operation(summary = "add multiple departments to employee", description = "This endpoint allows you to assign multiple departments to an employee.")
    @PutMapping("/relationship/employees/{empId}/multiple-departments")
    public ResponseEntity<EmployeeWithDepartmentDto> addEmployeeToDepartments(@PathVariable int empId,
            @RequestBody List<Integer> deptIds) {
        EmployeeWithDepartmentDto employeeWithDepartmentDto = relationShipService.addEmployeeToDepartments(empId,
                deptIds);
        return ResponseEntity.ok(employeeWithDepartmentDto);
    }
}
