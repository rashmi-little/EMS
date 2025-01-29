package com.mindfire.ems.dto;

import java.time.LocalDate;

public record EmployeeResponseDto(int id, String name, String email, double salary, LocalDate dateOfJoining) {

}
