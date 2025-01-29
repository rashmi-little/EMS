package com.mindfire.ems.dto;

import java.time.LocalDate;

public record EmployeeRequestDto(String name,
        String email,
        double salary,
        LocalDate dateOfJoining
        ) {
}
