package com.mindfire.ems.dto;

import java.time.LocalDate;
import java.util.List;

public record EmployeeRequestDto(String name,
        String email,
        double salary,
        LocalDate dateOfJoining,
        List<Integer> departmentIds) {
}
