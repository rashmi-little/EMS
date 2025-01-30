package com.mindfire.ems.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DepartmentRequestDto(
        @NotNull @NotBlank String name,
        @NotNull @NotBlank String location) {
}