package com.mindfire.ems.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
