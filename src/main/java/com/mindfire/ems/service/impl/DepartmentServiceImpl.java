package com.mindfire.ems.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mindfire.ems.model.Department;
import com.mindfire.ems.repository.DepartmentRepository;
import com.mindfire.ems.service.DepartmentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Override
    public Department addDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Department updateDepartment(int id, Department department) {
        Department oldDepartment = getDepartment(id);
        oldDepartment.setLocation(department.getLocation());
        oldDepartment.setName(department.getName());

        return departmentRepository.save(oldDepartment);
    }

    @Override
    public void deleteDepartment(int id) {
        departmentRepository.deleteById(id);
    }

    @Override
    public List<Department> getDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public Department getDepartment(int id) {
        return departmentRepository.findById(id).orElseThrow(() -> new RuntimeException("department not found"));
    }

}
