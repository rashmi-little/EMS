package com.mindfire.ems.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindfire.ems.dto.DepartmentRequestDto;
import com.mindfire.ems.dto.DepartmentResponseDto;
import com.mindfire.ems.dto.PagingResult;
import com.mindfire.ems.service.DepartmentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Tag(name = "Department APIs", description = "Department related operation")
public class DepartmentController {

    private final DepartmentService departmentService;

    @Operation(summary = "Create a new department", description = "This endpoint is used to create a new department.")
    @PostMapping("/departments")
    public ResponseEntity<DepartmentResponseDto> createDepartment(@Valid @RequestBody DepartmentRequestDto dto) {
        DepartmentResponseDto department = departmentService.addDepartment(dto);

        return new ResponseEntity<DepartmentResponseDto>(department, HttpStatus.CREATED);
    }

    @Operation(summary = "Get all departments", description = "This endpoint fetches all the departments in the system.")
    @GetMapping("/departments")
    public ResponseEntity<List<DepartmentResponseDto>> getAll() {
        List<DepartmentResponseDto> departments = departmentService.getDepartments();

        return ResponseEntity.ok(departments);
    }

    @Operation(summary = "Get department by ID", description = "This endpoint fetches the department details by its ID.")
    @GetMapping("/departments/{id}")
    public ResponseEntity<DepartmentResponseDto> getDepartmentById(@PathVariable int id) {
        DepartmentResponseDto department = departmentService.getDepartment(id);

        return ResponseEntity.ok(department);
    }

    @Operation(summary = "Remove department by ID", description = "This endpoint removes the department based on the provided department ID.")
    @DeleteMapping("/departments/{id}")
    public ResponseEntity<Void> removeDepartment(@PathVariable int id) {
        boolean result = departmentService.deleteDepartment(id);

        return result ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Update department by ID", description = "This endpoint allows you to update department details based on the provided department ID.")
    @PutMapping("/departments/{id}")
    public ResponseEntity<DepartmentResponseDto> updateDepartment(@PathVariable int id,
            @RequestBody DepartmentRequestDto dto) {
        DepartmentResponseDto updatedDepartment = departmentService.updateDepartment(id, dto);

        return ResponseEntity.ok(updatedDepartment);
    }

    @Operation(summary = "Get employee count per department", description = "This endpoint provides the count of employees in each department.")
    @GetMapping("/departments/employee-count")
    public ResponseEntity<List<Object[]>> countEmployeePerDepartment() {
        List<Object[]> list = departmentService.employeePerDepartment();
        return ResponseEntity.ok(list);
    }

    @Operation(summary = "Get pages of departments in the batch of 5", description = "This endpoint provides the batch fetching of departments.")
    @GetMapping("/departments/batch/{pageNumber}")
    public ResponseEntity<PagingResult<DepartmentResponseDto>> getDepartmentsInBatch(@PathVariable int pageNumber) {
        PagingResult<DepartmentResponseDto> departments = departmentService.getDepartmentsInBatch(pageNumber);
        return ResponseEntity.ok(departments);
    }

}
