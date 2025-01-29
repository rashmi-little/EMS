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
import com.mindfire.ems.model.Employee;
import com.mindfire.ems.service.EmployeeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping("/employee")
    public ResponseEntity<EmployeeResponseDto> createEmployee(@RequestBody EmployeeRequestDto dto) {
        var savedEmployee = employeeService.addEmployee(dto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @GetMapping("/employee")
    public ResponseEntity<List<EmployeeResponseDto>> getAll() {
        var employees = employeeService.getEmployees();

        return ResponseEntity.ok(employees);
    }

    @GetMapping("/employee/{id}")
    public ResponseEntity<EmployeeResponseDto> getAll(@PathVariable int id) {
        var employee = employeeService.getEmployee(id);

        return ResponseEntity.ok(employee);
    }

    @DeleteMapping("/employee/{id}")
    public ResponseEntity<Void> removeEmployee(@PathVariable int id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/employee/{id}/salary/{amount}")
    public ResponseEntity<EmployeeResponseDto> updateEmployeeSalary(@PathVariable int id, @PathVariable double amount) {
        var updatedEmployee = employeeService.updateEmployeeSalary(id, amount);
        return ResponseEntity.ok(updatedEmployee);
    }

    @GetMapping("/employee/salary-greater-than/{amount}")
    public ResponseEntity<List<EmployeeResponseDto>> getEmployeesSalaryGreaterThan(@PathVariable double amount) {
        List<EmployeeResponseDto> employees = employeeService.getEmployeeWithSalaryGreaterThan(amount);

        return ResponseEntity.ok(employees);
    }

    @GetMapping("/employee/joined-in-last-six-months")
    public ResponseEntity<List<EmployeeResponseDto>> joinedInPastSixMonths() {
        List<EmployeeResponseDto> employees = employeeService.getAllEmployeeJoinedInlastSixMonth();

        return ResponseEntity.ok(employees);
    }

    @GetMapping("/employee/high-paying")
    public ResponseEntity<List<EmployeeResponseDto>> highPayingEmployee() {
        List<EmployeeResponseDto> response = employeeService.earningMorethanThirdHighestSalary();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/employee/bonus")
    public ResponseEntity<Void> bulkSalaryUpdate() {
        employeeService.bulkSalaryUpdate();

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
