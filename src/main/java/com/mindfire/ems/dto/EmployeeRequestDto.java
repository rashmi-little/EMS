package com.mindfire.ems.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record EmployeeRequestDto(
                @NotNull @NotBlank String name,
                @NotNull @NotBlank @Email String email,
                @NotNull @Positive double salary,
                @NotNull LocalDate dateOfJoining) {
}
