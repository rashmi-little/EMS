package com.mindfire.ems.service;

import java.util.List;

import com.mindfire.ems.dto.DepartmentRequestDto;
import com.mindfire.ems.dto.DepartmentResponseDto;

public interface DepartmentService {
    DepartmentResponseDto addDepartment(DepartmentRequestDto dto);

    DepartmentResponseDto updateDepartment(int id, DepartmentRequestDto dto);

    void deleteDepartment(int id);

    List<DepartmentResponseDto> getDepartments();

    DepartmentResponseDto getDepartment(int id);
}
