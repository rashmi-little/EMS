package com.mindfire.ems.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindfire.ems.dto.DepartmentResponseDto;
import com.mindfire.ems.dto.EmployeeResponseDto;
import com.mindfire.ems.dto.EmployeeWithDepartmentDto;
import com.mindfire.ems.service.RelationShipService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class RelationShipController {
    private final RelationShipService relationShipService;

    @PostMapping("/relationship/employee/{empId}/department/{deptId}")
    public ResponseEntity<String> addEmployeeToDepartment(@PathVariable int empId, @PathVariable int deptId) {
        boolean status = relationShipService.addEmployeeToDepartment(empId, deptId);

        if (status) {
            return ResponseEntity.ok("Employee added to department successfully");
        }

        return ResponseEntity.status(HttpStatus.CONFLICT).body("Employee already in the department");
    }

    @GetMapping("/relationship/employees/{deptId}")
    public ResponseEntity<List<EmployeeResponseDto>> getAllEmployeesByDepartmentId(@PathVariable int deptId) {
        List<EmployeeResponseDto> employees = relationShipService.getAllEmployee(deptId);

        return ResponseEntity.ok(employees);
    }

    @GetMapping("/relationship/departments/{empId}")
    public ResponseEntity<List<DepartmentResponseDto>> getAllDepartmentsAssociatedWithEmployee(
            @PathVariable int empId) {
        List<DepartmentResponseDto> departments = relationShipService.getAllAssociatedDepartments(empId);

        return ResponseEntity.ok(departments);
    }

    @DeleteMapping("/relationship/employee/{empId}/department/{deptId}")
    public ResponseEntity<Void> removeEmployeeFromDepartment(@PathVariable int empId, @PathVariable int deptId) {
        boolean result = relationShipService.removeEmployeeFromDepartment(empId, deptId);

        if (result) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/relationship/transfer/employee/{empId}/from/{fromDeptId}/to/{toDeptId}")
    public ResponseEntity<Void> transferEmployee(@PathVariable int empId, @PathVariable int fromDeptId,
            @PathVariable int toDeptId) {

        relationShipService.transfer(empId, fromDeptId, toDeptId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/relationship/employees")
    public ResponseEntity<List<EmployeeWithDepartmentDto>> getEmployees() {
        List<EmployeeWithDepartmentDto> response = relationShipService.getAllEmployeesWithDepartments();

        return ResponseEntity.ok(response);
    }
}
