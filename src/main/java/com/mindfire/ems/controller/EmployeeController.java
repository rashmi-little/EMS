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
import com.mindfire.ems.service.EmployeeService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/employees")
    public ResponseEntity<EmployeeResponseDto> createEmployee(@Valid @RequestBody EmployeeRequestDto dto) {
        EmployeeResponseDto savedEmployee = employeeService.addEmployee(dto);

        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeResponseDto>> getAll() {
        List<EmployeeResponseDto> employees = employeeService.getEmployees();

        return ResponseEntity.ok(employees);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<EmployeeResponseDto> getAll(@PathVariable int id) {
        EmployeeResponseDto employee = employeeService.getEmployee(id);

        return ResponseEntity.ok(employee);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Void> removeEmployee(@PathVariable int id) {
        boolean result = employeeService.deleteEmployee(id);
        
        return result ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @PatchMapping("/employees/{id}/salary/{amount}")
    public ResponseEntity<EmployeeResponseDto> updateEmployeeSalary(@PathVariable int id, @PathVariable double amount) {
        EmployeeResponseDto updatedEmployee = employeeService.updateEmployeeSalary(id, amount);

        return ResponseEntity.ok(updatedEmployee);
    }

    @GetMapping("/employees/salary-greater-than/{amount}")
    public ResponseEntity<List<EmployeeResponseDto>> getEmployeesSalaryGreaterThan(@PathVariable double amount) {
        List<EmployeeResponseDto> employees = employeeService.getEmployeeWithSalaryGreaterThan(amount);

        return ResponseEntity.ok(employees);
    }

    @GetMapping("/employees/joined-in-last-six-months")
    public ResponseEntity<List<EmployeeResponseDto>> joinedInPastSixMonths() {
        List<EmployeeResponseDto> employees = employeeService.getAllEmployeeJoinedInlastSixMonth();

        return ResponseEntity.ok(employees);
    }

    @GetMapping("/employees/high-paying")
    public ResponseEntity<List<EmployeeResponseDto>> highPayingEmployee() {
        List<EmployeeResponseDto> response = employeeService.earningMorethanThirdHighestSalary();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/employees/bonus/{incrementPercentage}")
    public ResponseEntity<Void> bulkSalaryUpdate(@PathVariable double incrementPercentage) {
        employeeService.bulkSalaryUpdate(incrementPercentage);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/employees/batch/{pageNumber}")
    public ResponseEntity<List<EmployeeResponseDto>> getEmployeesInBatch(@PathVariable int pageNumber) {
        List<EmployeeResponseDto> batchOfEmployee = employeeService.getEmployeeInBatchSortBySalaryInDesc(pageNumber);

        return ResponseEntity.ok(batchOfEmployee);
    }
}
