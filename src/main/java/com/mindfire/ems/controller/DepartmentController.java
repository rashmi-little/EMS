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
import com.mindfire.ems.model.Department;
import com.mindfire.ems.service.DepartmentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping("/department")
    public ResponseEntity<Department> createDepartment(@RequestBody DepartmentRequestDto dto) {
        Department department = departmentService.addDepartment(dto);

        return new ResponseEntity<Department>(department, HttpStatus.CREATED);
    }

    @GetMapping("/department")
    public ResponseEntity<List<Department>> getAll() {
        List<Department> departments = departmentService.getDepartments();

        return ResponseEntity.ok(departments);
    }

    @GetMapping("/department/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable int id) {
        Department department = departmentService.getDepartment(id);

        return ResponseEntity.ok(department);
    }

    @DeleteMapping("/department/{id}")
    public ResponseEntity<Void> removeDepartment(@PathVariable int id) {
        departmentService.deleteDepartment(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/department/{id}")
    public ResponseEntity<Department> updateDepartment(@PathVariable int id, @RequestBody DepartmentRequestDto dto) {
        Department updatedDepartment = departmentService.updateDepartment(id, dto);

        return ResponseEntity.ok(updatedDepartment);
    }

}
