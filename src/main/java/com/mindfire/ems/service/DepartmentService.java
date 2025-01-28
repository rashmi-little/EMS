package com.mindfire.ems.service;

import java.util.List;

import com.mindfire.ems.model.Department;

public interface DepartmentService {
    Department addDepartment(Department department);

    Department updateDepartment(int id, Department department);

    void deleteDepartment(int id);

    List<Department> getDepartments();

    Department getDepartment(int id);
}
