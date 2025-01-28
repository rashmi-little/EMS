package com.mindfire.ems.service;

import java.util.List;

import com.mindfire.ems.dto.DepartmentRequestDto;
import com.mindfire.ems.model.Department;

public interface DepartmentService {
    Department addDepartment(DepartmentRequestDto dto);

    Department updateDepartment(int id, DepartmentRequestDto dto);

    void deleteDepartment(int id);

    List<Department> getDepartments();

    Department getDepartment(int id);
}
